import kotlin.math.max

/*
* 백준 1937번. 욕심쟁이 판다
* https://www.acmicpc.net/problem/1937
*/

data class Position(val i: Int, val j: Int)

private var n = -1
private lateinit var map: Array<IntArray>

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private const val NOTYETSEARCH = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    map = Array(n) { IntArray(n) { -1 } }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until n) {
            map[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    val dp = findNumberOfMove()

    var result = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (result < dp[i][j]) result = dp[i][j]
        }
    }

    return result
}

// 각 위치에서 최대로 움직일 수 있는 횟수를 찾아주는 함수
private fun findNumberOfMove(): Array<IntArray> {
    val dp = Array(n) { IntArray(n) { NOTYETSEARCH } }

    fun dfs(pos: Position): Int {
        // 이미 탐색해서 최대로 몇 번 갈 수 있는지 아는 경우
        if (dp[pos.i][pos.j] != NOTYETSEARCH) {
            return dp[pos.i][pos.j]
        }

        var canSearch = false
        for (k in 0 until 4) {
            val ni = pos.i + di[k]
            val nj = pos.j + dj[k]

            if (isInRange(ni, nj) && map[pos.i][pos.j] < map[ni][nj]) {
                canSearch = true
                dfs(Position(ni, nj))
                dp[pos.i][pos.j] = max(dp[pos.i][pos.j], dp[ni][nj] + 1)
            }
        }

        // 더 이상 탐색이 불가능한 경우. 즉 판다가 이동할 수 없는 경우
        if (!canSearch) {
            dp[pos.i][pos.j] = 1
            return 0
        }

        return dp[pos.i][pos.j]
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (dp[i][j] == NOTYETSEARCH) {
                dfs(Position(i, j))
            }
        }
    }

    return dp
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until n && j in 0 until n
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
