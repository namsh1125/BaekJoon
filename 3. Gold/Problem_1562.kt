/*
* 백준 1562번. 계단 수
* https://www.acmicpc.net/problem/1562
*/

private const val MOD = 1_000_000_000

private fun main() {
    val n = readln().toInt()
    val result = getResult(n)
    printResult(result)
}

private fun getResult(n: Int): Long {
    val dp = Array(n + 1) { Array(10) { IntArray(1 shl 10) { 0 } } }

    for (j in 1..9) { // last
        dp[1][j][1 shl j] = 1
    }

    for (i in 2..n) { // length
        for (j in 0..9) { // last
            for (bit in 0 until (1 shl 10)) {
                when (j) {
                    0 -> dp[i][0][bit or (1 shl 0)] += dp[i - 1][1][bit]
                    9 -> dp[i][9][bit or (1 shl 9)] += dp[i - 1][8][bit]
                    else -> dp[i][j][bit or (1 shl j)] += (dp[i - 1][j - 1][bit] + dp[i - 1][j + 1][bit]) % MOD
                }
                dp[i][j][bit] %= MOD
            }
        }
    }

    var result = 0L
    for (j in 0..9) {
        result = (result + dp[n][j][(1 shl 10) - 1]) % MOD
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
