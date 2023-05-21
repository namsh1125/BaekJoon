import kotlin.math.max
import kotlin.math.min

/*
* 백준 2118번. 두 개의 탑
* https://www.acmicpc.net/problem/2118
*/

private var n = -1
private lateinit var dist: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    dist = IntArray(n) { -1 }
    repeat(n) { i ->
        dist[i] = br.readLine().toInt()
    }

    br.close()
}

private fun getResult(): Int {
    val pSum = getPrefixSum()
    val sum = dist.sum()

    var result = dist.min()
    for (i in 1 until n) {
        for (j in i + 1..n) {
            // 두 탑의 거리: i - j 반시계 & i -j 시계의 최솟값
            val dist1 = pSum[j] - pSum[i - 1]
            val dist2 = sum - dist1

            // 두 탑의 거리의 최댓값 구하기
            result = max(result, min(dist1, dist2))
        }
    }

    return result
}

private fun getPrefixSum(): IntArray {
    val pSum = IntArray(n + 1) { 0 }

    for (i in 1..n) {
        pSum[i] = pSum[i - 1] + dist[i - 1]
    }

    return pSum
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
