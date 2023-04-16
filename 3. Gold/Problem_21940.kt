import kotlin.math.max
import kotlin.math.min

/*
* 백준 21940번. 가운데에서 만나기
* https://www.acmicpc.net/problem/21940
*/

private var n = -1 // 도시의 개수
private var m = -1 // 도로의 개수
private lateinit var time: Array<LongArray>

private var k = -1 // 친구들의 총 인원
private lateinit var live: IntArray // 친구들이 살고 있는 도시의 번호

private const val INF = 1000 * 200 * (200 - 1) + 1L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        m = it[1]
    }

    time = Array(n) { LongArray(n) { INF } }
    for (i in 0 until n) {
        time[i][i] = 0
    }

    repeat(m) {
        val (a, b, t) = br.readLine().split(' ').map { it.toInt() }
        time[a - 1][b - 1] = t.toLong()
    }

    k = br.readLine().toInt()
    live = IntArray(k)

    br.readLine().split(' ').map { it.toInt() }.forEachIndexed { index, town ->
        live[index] = town - 1
    }

    br.close()
}

private fun getResult(): ArrayList<Int> {
    floydWarshall()
    val duration = roundTripDuration()
    val max = durationSum(duration)
    return getReulst(max)
}

private fun floydWarshall() {
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                time[i][j] = min(time[i][j], time[i][k] + time[k][j])
            }
        }
    }
}

// 다른 도시까지 이동하는데 걸리는 시간을 입력받아 왕복하는데 걸리는 시간을 return 하는 함수
private fun roundTripDuration(): Array<LongArray> {
    val duration = Array(k) { LongArray(n) { 0 } }

    for (i in 0 until k) {
        val start = live[i] // i번째 학생이 살고 있는 도시

        for (j in 0 until n) {
            if (j == start) continue
            duration[i][j] = min(time[start][j] + time[j][start], INF)
        }
    }

    return duration
}

// 모든 친구들의 특정 도시 왕복 시간의 최대값을 구하는 함수
private fun durationSum(duration: Array<LongArray>): LongArray {
    val max = LongArray(n) { 0 }

    for (i in 0 until k) {
        for (j in 0 until n) {
            max[j] = max(max[j], duration[i][j])
        }
    }

    return max
}

// 왕복 시간의 최댓값이 가장 작은 도시를 구하는 함수
private fun getReulst(max: LongArray): ArrayList<Int> {
    val result = ArrayList<Int>()

    val min = max.min()
    for (i in max.indices) {
        if (max[i] == min) result.add(i + 1)
    }

    return result
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
