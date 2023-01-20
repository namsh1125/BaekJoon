import kotlin.math.max

/*
* 백준 1987번. 알파벳
* https://www.acmicpc.net/problem/1987
*/

private lateinit var map: Array<CharArray>
private val visited = BooleanArray(size = 26) { false }
private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)
private var result = 1

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (r, c) = br.readLine().split(' ').map { it.toInt() }
    map = Array(size = r) { CharArray(size = c) { ' ' } }

    for (i in map.indices) {
        val line = br.readLine()
        for (j in map[i].indices) {
            map[i][j] = line[j]
        }
    }
}

private fun getResult() {
    return dfs(0, 0, 0)
}

private fun dfs(i: Int, j: Int, count: Int) {

    if (visited[map[i][j] - 'A']) {
        result = max(result, count)
        return
    }

    visited[map[i][j] - 'A'] = true

    for (k in 0 until 4) {
        val ni = i + di[k]
        val nj = j + dj[k]

        if (isInRange(ni, nj)) {
            dfs(ni, nj, count + 1)
        }
    }

    visited[map[i][j] - 'A'] = false
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
