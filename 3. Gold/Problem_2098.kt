import kotlin.math.min

/*
* 백준 2098번. 외판원 순회
* https://www.acmicpc.net/problem/2098
*/

private var n = 0
private lateinit var cost: Array<IntArray>

private const val INF = 16 * 1_000_000

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    cost = Array(n) { IntArray(n) { INF } }
    for (i in cost.indices) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in cost[i].indices) {
            cost[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    val dp = Array(n) { IntArray((1 shl n) - 1) { -1 } }

    fun dfs(city: Int, visited: Int): Int {
        if (visited == (1 shl n) - 1) {
            return if (cost[city][0] == 0) INF else cost[city][0]
        }

        if (dp[city][visited] != -1) return dp[city][visited]
        dp[city][visited] = INF

        for (i in 0 until n) {
            if (visited and (1 shl i) == 0 && cost[city][i] != 0) {
                dp[city][visited] = min(dp[city][visited], dfs(i, visited or (1 shl i)) + cost[city][i])
            }
        }

        return dp[city][visited]
    }

    return dfs(0, 1)
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
