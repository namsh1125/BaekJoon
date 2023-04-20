/*
* 백준 1726번. 로봇
* https://www.acmicpc.net/problem/1726
*/

data class Position(val i: Int, val j: Int, val dir: Int, val instruction: Int)

private var n = -1
private var m = -1
private lateinit var map: Array<IntArray>

private lateinit var start: Position
private lateinit var end: Position

private const val EAST = 0
private const val WEST = 1
private const val SOUTH = 2
private const val NORTH = 3

private const val WALL = 1

private val di = arrayOf(0, 0, 1, -1) // 동, 서, 남, 북
private val dj = arrayOf(1, -1, 0, 0) // 동, 서, 남, 북

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        m = it[1]
    }

    map = Array(n) { IntArray(m) { -1 } }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until m) {
            map[i][j] = line[j]
        }
    }

    br.readLine().split(' ').map { it.toInt() - 1 }.also {
        start = Position(it[0], it[1], it[2], 0)
    }

    br.readLine().split(' ').map { it.toInt() - 1 }.also {
        end = Position(it[0], it[1], it[2], 0)
    }

    br.close()
}

private fun getResult(): Int {
    val queue = ArrayDeque<Position>()
    val visited = Array(n) { Array(m) { BooleanArray(4) { false } } }
    visited[start.i][start.j][start.dir] = true

    queue.add(start)
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        if (finish(cur)) return cur.instruction

        for (k in 1..3) {
            val pos = Position(cur.i + di[cur.dir] * k, cur.j + dj[cur.dir] * k, cur.dir, cur.instruction + 1)
            if (isInRange(pos.i, pos.j) && map[pos.i][pos.j] != WALL) {
                if (!visited[pos.i][pos.j][pos.dir]) {
                    visited[pos.i][pos.j][pos.dir] = true
                    queue.add(pos)
                }
            } else {
                break
            }
        }

        when (cur.dir) {
            EAST, WEST -> {
                if (!visited[cur.i][cur.j][SOUTH]) {
                    visited[cur.i][cur.j][SOUTH] = true
                    queue.add(Position(cur.i, cur.j, SOUTH, cur.instruction + 1))
                }
                if (!visited[cur.i][cur.j][NORTH]) {
                    visited[cur.i][cur.j][NORTH] = true
                    queue.add(Position(cur.i, cur.j, NORTH, cur.instruction + 1))
                }
            }

            SOUTH, NORTH -> {
                if (!visited[cur.i][cur.j][EAST]) {
                    visited[cur.i][cur.j][EAST] = true
                    queue.add(Position(cur.i, cur.j, EAST, cur.instruction + 1))
                }

                if (!visited[cur.i][cur.j][WEST]) {
                    visited[cur.i][cur.j][WEST] = true
                    queue.add(Position(cur.i, cur.j, WEST, cur.instruction + 1))
                }
            }
        }
    }

    return -1
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until n && j in 0 until m
}

private fun finish(pos: Position): Boolean {
    return pos.i == end.i && pos.j == end.j && pos.dir == end.dir
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
