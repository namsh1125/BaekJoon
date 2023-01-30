import kotlin.math.min

/*
* 백준 1149번. RGB거리
* https://www.acmicpc.net/problem/1149
*/

data class Cost(val red: Int, val green: Int, val blue: Int)

private val costList = arrayListOf<Cost>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    repeat(n) {
        val (r, g, b) = br.readLine().split(' ').map { it.toInt() }
        costList.add(Cost(r, g, b))
    }
}

private fun getResult(): Int {

    val n = costList.size
    val dp = Array(size = n) { Array(size = 3) { Int.MAX_VALUE } }
    dp[0][0] = costList[0].red
    dp[0][1] = costList[0].green
    dp[0][2] = costList[0].blue

    for (i in 1 until costList.size) {
        dp[i][0] = min(dp[i - 1][1] + costList[i].red, dp[i - 1][2] + costList[i].red)
        dp[i][1] = min(dp[i - 1][0] + costList[i].green, dp[i - 1][2] + costList[i].green)
        dp[i][2] = min(dp[i - 1][0] + costList[i].blue, dp[i - 1][1] + costList[i].blue)
    }

    return min(dp[n - 1][0], min(dp[n - 1][1], dp[n - 1][2]))
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
