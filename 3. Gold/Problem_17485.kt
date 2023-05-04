import kotlin.math.min

/*
* 백준 17485번. 진우의 달 여행 (Large)
* https://www.acmicpc.net/problem/17485
*/

private var n = -1
private var m = -1
private lateinit var fuel: Array<IntArray>

private const val LEFT = 0
private const val CENTER = 1
private const val RIGHT = 2

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

    fuel = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until m) {
            fuel[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    val dp = Array(n) { Array(m) { IntArray(3) { Int.MAX_VALUE } } }

    for (j in 0 until m) {
        for (k in 0 until 3) {
            dp[0][j][k] = fuel[0][j]
        }
    }

    for (i in 1 until n) {
        for (j in 0 until m) {
            if (j + 1 < m) {
                dp[i][j][LEFT] = min(dp[i - 1][j + 1][CENTER], dp[i - 1][j + 1][RIGHT]) + fuel[i][j]
            }

            dp[i][j][CENTER] = min(dp[i - 1][j][LEFT], dp[i - 1][j][RIGHT]) + fuel[i][j]

            if (j - 1 >= 0) {
                dp[i][j][RIGHT] = min(dp[i - 1][j - 1][LEFT], dp[i - 1][j - 1][CENTER]) + fuel[i][j]
            }
        }
    }

    var result = Int.MAX_VALUE
    for (j in 0 until m) {
        for (k in 0 until 3) {
            result = min(result, dp[n - 1][j][k])
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
