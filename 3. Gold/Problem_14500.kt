import java.util.StringTokenizer
import kotlin.math.max

/*
* 백준 14500번. 테트로미노
* https://www.acmicpc.net/problem/14500
*/

private lateinit var map: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)
private var result = Int.MIN_VALUE

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    map = Array(n) { IntArray(m) { -1 } }
    for (i in map.indices) {
        val st = StringTokenizer(br.readLine())

        for (j in map[i].indices) {
            map[i][j] = st.nextToken().toInt()
        }
    }

    visited = Array(n) { BooleanArray(m) { false } }

    br.close()
}

private fun getResult() {
    for (i in map.indices) {
        for (j in map[i].indices) {
            visited[i][j] = true
            dfs(i, j, map[i][j], 1)
            visited[i][j] = false
        }
    }
}

private fun dfs(i: Int, j: Int, sum: Int, count: Int) {

    if (count == 4) {
        result = max(result, sum)
        return
    }

    for (k in 0 until 4) {
        val ni = i + di[k]
        val nj = j + dj[k]

        if (isInRange(ni, nj) && !visited[ni][nj]) {

            if (count == 2) { // 'ㅗ' 테트로미노를 만들기 위해 2번째 칸에서 별도로 탐색 진행
                visited[ni][nj] = true
                dfs(i, j, sum + map[ni][nj], count + 1)
                visited[ni][nj] = false
            }

            visited[ni][nj] = true
            dfs(ni, nj, sum + map[ni][nj], count + 1)
            visited[ni][nj] = false
        }
    }

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
