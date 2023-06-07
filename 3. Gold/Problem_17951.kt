import java.util.StringTokenizer

/*
* 백준 17951번. 흩날리는 시험지 속에서 내 평점이 느껴진거야
* https://www.acmicpc.net/problem/17951
*/

private lateinit var score: IntArray
private var n = -1
private var k = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        k = it[1]
    }

    score = IntArray(n) { 0 }

    val st = StringTokenizer(br.readLine())
    repeat(n) { i ->
        score[i] = st.nextToken().toInt()
    }

    br.close()
}

private fun getResult(): Int {
    var low = 0
    var high = score.sum()

    while (low <= high) {
        val mid = (low + high) / 2
        var count = 0
        var total = 0

        // mid보다 더 높은 점수를 얻는 그룹의 개수 구하기
        for (i in score.indices) {
            total += score[i]

            if (total >= mid) {
                count++
                total = 0
            }
        }

        if (count >= k) low = mid + 1 // 목표하는 그룹의 개수가 k개 이상인 경우, 목표치 상승
        else high = mid - 1 // 목표하는 그룹의 개수가 k개 미만인 경우, 목표치 하향
    }

    return high
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
