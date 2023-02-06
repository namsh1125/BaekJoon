/*
* 백준 17070번. 파이프 옮기기 1
* https://www.acmicpc.net/problem/17070
*/

private lateinit var house: Array<IntArray>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    house = Array(n) { IntArray(n) { -1 } }
    for (i in house.indices) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in house[i].indices) {
            house[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    val n = house.size
    val dp = Array(n) { Array(n) { IntArray(3) { 0 } } } // 0: 가로, 1: 세로, 2: 대각선
    dp[0][1][0] = 1

    for (j in 2 until n) {
        if (house[0][j] == 1) break
        dp[0][j][0] = 1
    }

    for (i in 1 until n) {
        for (j in 1 until n) {
            if (house[i][j] == 0 && house[i - 1][j] == 0 && house[i][j - 1] == 0) {
                dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]
            }
            if (house[i][j] == 0) {
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2]
                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2]
            }
        }
    }

    return dp[n - 1][n - 1].sum()
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
