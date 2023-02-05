import java.util.StringTokenizer
import kotlin.math.max

/*
* 백준 9465번. 스티커
* https://www.acmicpc.net/problem/9465
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = ArrayList<Int>()
    val tc = br.readLine().toInt()

    repeat(tc) {
        val n = br.readLine().toInt()

        val sticker = Array(2) { IntArray(n) { -1 } }
        for (i in 0 until 2) {
            val st = StringTokenizer(br.readLine())
            for (j in 0 until n) {
                sticker[i][j] = st.nextToken().toInt()
            }
        }

        result.add(getResult(sticker))
    }
    br.close()

    printResult(result)
}

private fun getResult(sticker: Array<IntArray>): Int {

    val n = sticker.first().size
    val dp = Array(2) { IntArray(n) { 0 } }
    dp[0][0] = sticker[0][0]
    dp[1][0] = sticker[1][0]

    if (dp[0].size > 1) {
        dp[0][1] = dp[1][0] + sticker[0][1]
        dp[1][1] = dp[0][0] + sticker[1][1]
    }

    for (i in 2 until n) {
        dp[0][i] = max(dp[1][i - 1], max(dp[0][i - 2], dp[1][i - 2])) + sticker[0][i]
        dp[1][i] = max(dp[0][i - 1], max(dp[0][i - 2], dp[1][i - 2])) + sticker[1][i]
    }

    return max(dp[0][n - 1], dp[1][n - 1])
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
