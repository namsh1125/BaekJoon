/*
* 백준 2178번. 미로 탐색
* https://www.acmicpc.net/problem/2178
*/

data class Position(val i: Int, val j: Int, val time: Int)

private lateinit var maze: Array<IntArray>

val di = arrayOf(1, -1, 0, 0)
val dj = arrayOf(0, 0, 1, -1)

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    maze = Array(size = n) { IntArray(size = m) { 0 } }
    for (i in maze.indices) {

        val line = br.readLine()
        for (j in maze[i].indices) {
            maze[i][j] = line[j] - '0'
        }
    }
}

fun getResult(): Int {

    val visited = Array(size = maze.size) { BooleanArray(size = maze.first().size) { false } }
    val queue = ArrayDeque<Position>()

    visited[0][0] = true
    queue.add(Position(0, 0, 1))

    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()
        for (k in 0 until 4) {

            val di = top.i + di[k]
            val dj = top.j + dj[k]

            if (di == maze.size - 1 && dj == maze.first().size - 1) return top.time + 1

            if (isInRange(di, dj) && !visited[di][dj] && maze[di][dj] == 1) {
                queue.add(Position(di, dj, top.time + 1))
                visited[di][dj] = true
            }
        }
    }

    return -1
}

fun isInRange(i: Int, j: Int): Boolean {
    return i in maze.indices && j in maze[i].indices
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
