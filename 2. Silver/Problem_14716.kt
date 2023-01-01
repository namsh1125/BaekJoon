import java.util.*

/*
* 백준 14716번. 현수막
* https://www.acmicpc.net/problem/14716
*/

private lateinit var banner: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private var dx = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
private var dy = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (m, n) = br.readLine().split(' ').map { it.toInt() }

    visited = Array(size = m) { BooleanArray(size = n) { false } }
    banner = Array(size = m) { CharArray(size = n) { '0' } }
    for (i in 0 until m) {

        var j = 0
        val st = StringTokenizer(br.readLine())
        while (st.hasMoreTokens()) {
            banner[i][j] = st.nextToken()[0]
            j++
        }
    }
}

fun getResult(): Int {

    var result = 0
    for (i in banner.indices) {
        for (j in banner[i].indices) {

            if (!visited[i][j] && banner[i][j] == '1') {
                visited[i][j] = true
                dfs(i, j)
                result++
            }
        }
    }

    return result
}

fun dfs(i: Int, j: Int) {

    for (k in 0 until 8) {

        val ni = i + dx[k]
        val nj = j + dy[k]

        if(!isInRange(ni,nj)) continue

        if (!visited[ni][nj] && banner[ni][nj] == '1') {

            visited[ni][nj] = true
            dfs(ni, nj)
        }
    }
}

fun isInRange(i: Int, j: Int): Boolean {
    return (i in banner.indices && j in banner[i].indices)
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
