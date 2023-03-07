import kotlin.math.min

/*
* 백준 2533번. 사회망 서비스(SNS)
* https://www.acmicpc.net/problem/2533
*/

private var n = -1
private lateinit var friends: Array<ArrayList<Int>>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    friends = Array(n + 1) { arrayListOf() }
    repeat(n - 1) {
        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        friends[u].add(v)
        friends[v].add(u)
    }

    br.close()
}

private fun getResult(): Int {
    val dp = Array(n + 1) { IntArray(2) { 0 } } // 0: 얼리 X, 1: 얼리 O
    val visited = BooleanArray(n + 1) { false }

    fun dfs(index: Int) {
        visited[index] = true
        dp[index][0] = 0
        dp[index][1] = 1

        for (friend in friends[index]) {
            if (!visited[friend]) {
                dfs(friend)
                dp[index][0] += dp[friend][1] // 본인이 얼리 어답터가 아니라면, 주변 친구들은 얼리 어답터여야 함
                dp[index][1] += min(dp[friend][0], dp[friend][1]) // 본인이 얼리 어답터라면 주변 친구들이 얼리 어답터든 아니든 상관없음
            }
        }
    }

    dfs(1)

    return min(dp[1][0], dp[1][1])
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
