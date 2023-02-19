/*
* 백준 16724번. 피리 부는 사나이
* https://www.acmicpc.net/problem/16724
*/

private lateinit var graph: Array<IntArray>

private const val NOTVISITED = 0
private const val VISITING = 1
private const val CANGOSAFEZONE = 2

private val di = arrayOf(-1, 1, 0, 0)
private val dj = arrayOf(0, 0, -1, 1)

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(n) { IntArray(m) { 0 } }

    for (i in graph.indices) {
        val line = br.readLine()
        for (j in graph[i].indices) {
            when (line[j]) {
                'U' -> graph[i][j] = 0
                'D' -> graph[i][j] = 1
                'L' -> graph[i][j] = 2
                'R' -> graph[i][j] = 3
            }
        }
    }

    br.close()
}

private fun getResult(): Int {
    val visited = Array(graph.size) { IntArray(graph[0].size) { NOTVISITED } }
    var result = 0

    fun dfs(i: Int, j: Int) {
        visited[i][j] = VISITING

        val ni = i + di[graph[i][j]]
        val nj = j + dj[graph[i][j]]

        if (visited[ni][nj] == NOTVISITED) dfs(ni, nj)
        else if (visited[ni][nj] == VISITING) result++

        visited[i][j] = CANGOSAFEZONE
    }

    for (i in graph.indices) {
        for (j in graph[i].indices) {
            if (visited[i][j] == NOTVISITED) {
                dfs(i, j)
            }
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
