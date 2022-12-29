import java.util.*

/*
* 백준 1303번. 전쟁 - 전투
* https://www.acmicpc.net/problem/1303
*/

private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)

private lateinit var map: Array<Array<Char>>
private lateinit var visited: Array<Array<Boolean>>

fun main() {

    var powerW = 0
    var powerB = 0

    setMap()

    for (i in map.indices) {
        for (j in map[i].indices) {

            if (!visited[i][j]) {
                val k = dfs(char = map[i][j], row = i, column = j)

                when (map[i][j]) {
                    'W' -> powerW += k * k
                    'B' -> powerB += k * k
                }
            }
        }
    }

    println("$powerW $powerB")

}

fun dfs(char: Char, row: Int, column: Int): Int {

    visited[row][column] = true
    var ret = 1

    for(i in 0 until 4) {
        val nx = column +dx[i]
        val ny = row + dy[i]

        if(nx < 0 || nx >= visited[0].size || ny < 0 || ny >= visited.size)
            continue
        if(!visited[ny][nx] && map[ny][nx] == char)
            ret += dfs(char = map[ny][nx], row = ny, column = nx)
    }

    return ret
}


fun setMap() = with(Scanner(System.`in`.bufferedReader())) {

    val (column, row) = nextLine().split(' ').map { it.toInt() }
    map = Array(row) { Array(column) { ' ' } }
    visited = Array(row) { Array(column) { false } }

    // 맵 셋팅
    for (i in 0 until row) {

        val line = nextLine()

        for (j in 0 until column) {
            map[i][j] = line[j]
        }
    }

}
