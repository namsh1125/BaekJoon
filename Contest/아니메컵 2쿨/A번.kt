import java.util.StringTokenizer
import kotlin.math.abs

/*
* 백준 아니메컵 2쿨 A번 - Gorani Command
* https://www.acmicpc.net/contest/problem/939/1
*/

data class Position(val i: Int, val j: Int)

private lateinit var map: Array<IntArray>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    map = Array(n) { IntArray(m) { 0 } }
    repeat(n - 1) { i ->
        map[i][0] = br.readLine().toInt()
    }

    val st = StringTokenizer(br.readLine())
    for (j in 0 until m) {
        map[n - 1][j] = st.nextToken().toInt()
    }

    br.close()
}

private fun getResult(): Position {
    for (r in map.indices) {
        for (c in map[r].indices) {

            var flag = true
            for (i in map.indices) {
                if (map[i][0] != distance(i, 0, r, c)) {
                    flag = false
                    break
                }

                if (i == map.lastIndex) {
                    for (j in 1 until map[i].size) {
                        if (map[i][j] != distance(i, j, r, c)) {
                            flag = false
                            break
                        }
                    }
                }
            }

            if (flag) return Position(r + 1, c + 1)
        }
    }

    return Position(-1, -1)
}

private fun distance(i: Int, j: Int, r: Int, c: Int): Int {
    return abs(i - r) + abs(j - c)
}

private fun printResult(result: Position) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.i} ${result.j}\n")
    bw.flush()
    bw.close()
}
