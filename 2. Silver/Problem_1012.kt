/*
* 백준 1012번. 유기농 배추
* https://www.acmicpc.net/problem/1012
*/

private lateinit var farm: Array<IntArray>

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private fun main() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    val result = ArrayList<Int>()
    repeat(t) {
        val (m, n, k) = br.readLine().split(' ').map { it.toInt() }
        farm = Array(size = n) { IntArray(size = m) { 0 } }

        repeat(k) {
            val (x, y) = br.readLine().split(' ').map { it.toInt() }
            farm[y][x] = 1
        }

        result.add(getResult())
    }

    br.close()
    printResult(result)
}


private fun getResult(): Int {

    val visited = Array(size = farm.size) { BooleanArray(size = farm.first().size) { false } }
    var result = 0

    for (i in farm.indices) {
        for (j in farm[i].indices) {
            if (farm[i][j] == 1 && !visited[i][j]) {
                dfs(i, j, visited)
                result++
            }
        }
    }

    return result
}

private fun dfs(i: Int, j: Int, visited: Array<BooleanArray>) {

    for (k in 0 until 4) {

        val ni = i + di[k]
        val nj = j + dj[k]

        if (isInRange(ni, nj) && farm[ni][nj] == 1 && !visited[ni][nj]) {
            visited[ni][nj] = true
            dfs(ni, nj, visited)
        }
    }
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in farm.indices && j in farm[i].indices
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
