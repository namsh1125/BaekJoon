/*
* 백준 13460번. 구슬 탈출 2
* https://www.acmicpc.net/problem/13460
*/

data class Position(var i: Int, var j: Int)
data class Info(val red: Position, val blue: Position, val time: Int)

private var n = 0
private var m = 0
private lateinit var map: Array<CharArray>
private lateinit var visited: Array<Array<Array<BooleanArray>>>

private const val WALL = '#'
private const val HOLE = 'O'
private const val RED = 'R'
private const val BLUE = 'B'

private lateinit var red: Position
private lateinit var blue: Position
private lateinit var hole: Position

private val di = arrayOf(-1, 1, 0, 0)
private val dj = arrayOf(0, 0, -1, 1)

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toInt() }.apply {
        n = this[0]
        m = this[1]
    }

    map = Array(n) { CharArray(m) }
    visited = Array(n) { Array(m) { Array(n) { BooleanArray(m) { false } } } }

    for (i in map.indices) {
        val line = br.readLine()
        for (j in map[i].indices) {
            map[i][j] = line[j]

            when (map[i][j]) {
                RED -> red = Position(i, j)
                BLUE -> blue = Position(i, j)
                HOLE -> hole = Position(i, j)
            }
        }
    }

    br.close()
}

private fun getResult(): Int {
    val queue = ArrayDeque<Info>()
    queue.add(Info(red, blue, 0))

    fun bfs(): Int {
        while (queue.isNotEmpty() && queue.first().time <= 10) {
            val cur = queue.removeFirst()

            for (i in 0 until 4) {
                val (redPos, redCnt) = move(cur.red, i)
                val (bluePos, blueCnt) = move(cur.blue, i)

                if (map[redPos.i][redPos.j] == HOLE && map[bluePos.i][bluePos.j] != HOLE) return cur.time + 1
                else if (map[bluePos.i][bluePos.j] == HOLE) continue

                if (redPos == bluePos) {
                    if (redCnt > blueCnt) {
                        redPos.i -= di[i]
                        redPos.j -= dj[i]
                    } else {
                        bluePos.i -= di[i]
                        bluePos.j -= dj[i]
                    }
                }

                if (visited[redPos.i][redPos.j][bluePos.i][bluePos.j]) continue
                else {
                    visited[redPos.i][redPos.j][bluePos.i][bluePos.j] = true
                    queue.add(Info(redPos, bluePos, cur.time + 1))
                }
            }
        }

        return -1
    }

    val result = bfs()

    return if (result <= 10) result else -1
}

private fun move(pos: Position, dir: Int): Pair<Position, Int> {
    var i = pos.i
    var j = pos.j
    var moved = 0

    while (map[i + di[dir]][j + dj[dir]] != WALL && map[i][j] != HOLE) {
        i += di[dir]
        j += dj[dir]
        moved++
    }

    return Pair(Position(i, j), moved)
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
