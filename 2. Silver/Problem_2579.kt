import kotlin.math.max

/*
* 백준 2579번. 계단 오르기
* https://www.acmicpc.net/problem/2579
*/

private fun main() {
    val score = initVariable()
    val result = getResult(score)
    printResult(result)
}

private fun initVariable(): IntArray {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val score = IntArray(size = n + 1) { -1 }
    repeat(n) { i ->
        score[i + 1] = br.readLine().toInt()
    }

    br.close()
    return score
}

private fun getResult(score: IntArray): Int {

    if (score.size == 2) return score[1] // 계단이 한 개라면

    val dp = IntArray(size = score.size) { 0 }
    dp[1] = score[1]
    dp[2] = score[1] + score[2]

    for (i in 3 until score.size) {
        dp[i] = max(dp[i - 2], dp[i - 3] + score[i - 1]) + score[i]
    }

    return dp[dp.size - 1]
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
