/*
* 백준 2667번. 단지번호붙이기
* https://www.acmicpc.net/problem/2667
*/

private lateinit var map: Array<IntArray>

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    map = Array(size = n) { IntArray(size = n) { -1 } }
    for (i in map.indices) {
        val line = br.readLine()
        for (j in map[i].indices) {
            map[i][j] = line[j] - '0'
        }
    }

    br.close()
}

private fun getResult(): List<Int> {

    val result = arrayListOf<Int>()
    val visited = Array(size = map.size) { BooleanArray(size = map.size) { false } }

    for (i in map.indices) {
        for (j in map[i].indices) {

            if (map[i][j] == 1 && !visited[i][j]) {
                visited[i][j] = true
                result.add(dfs(i, j, visited))
            } else {
                visited[i][j] = true
            }
        }
    }

    return result.sorted()
}

private fun dfs(i: Int, j: Int, visited: Array<BooleanArray>): Int {

    var size = 1
    for (k in 0 until 4) {
        val ni = i + di[k]
        val nj = j + dj[k]

        if (isInRange(ni, nj) && map[ni][nj] == 1 && !visited[ni][nj]) {
            visited[ni][nj] = true
            size += dfs(ni, nj, visited)
        }
    }

    return size
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map.indices
}

private fun printResult(result: List<Int>) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.size}\n")
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
