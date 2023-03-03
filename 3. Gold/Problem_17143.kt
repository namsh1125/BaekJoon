/*
* 백준 17143번. 낚시왕
* https://www.acmicpc.net/problem/17143
*/

data class Position(var i: Int, var j: Int)
data class Shark(var index: Int, val pos: Position, val s: Int, var d: Int, val z: Int, var isLive: Boolean)

private val sharkList = ArrayList<Shark>()

private const val UP = 1
private const val DOWN = 2
private const val RIGHT = 3
private const val LEFT = 4

private var r = 0
private var c = 0
private lateinit var map: Array<IntArray>

private const val EMPTY = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    var m: Int
    br.readLine().split(' ').map { it.toInt() }.apply {
        r = this[0]
        c = this[1]
        m = this[2]
    }

    map = Array(r + 1) { IntArray(c + 1) { EMPTY } }

    repeat(m) { i ->
        val (r, c, s, d, z) = br.readLine().split(' ').map { it.toInt() }
        sharkList.add(Shark(i, Position(r, c), s, d, z, true))
        map[r][c] = i
    }

    br.close()
}

private fun getResult(): Int {
    var result = 0
    var pos = 0

    while (pos++ < c) {
        val shark = catchShark(pos)
        if (shark != null) {
            result += shark.z
            shark.isLive = false
        }

        move()
    }

    return result
}

private fun catchShark(j: Int): Shark? {
    for (i in map.indices) {
        if (map[i][j] != EMPTY) {
            val index = map[i][j]
            return sharkList[index]
        }
    }

    return null
}

private fun move() {
    val visited = Array(r + 1) { IntArray(c + 1) { EMPTY } }

    for (shark in sharkList) {
        if (!shark.isLive) continue

        move(shark)

        val pos = shark.pos
        if (visited[pos.i][pos.j] == EMPTY) {
            visited[pos.i][pos.j] = shark.index
        } else {
            val otherIndex = visited[pos.i][pos.j]
            val other = sharkList[otherIndex]

            if (other.z < shark.z) {
                other.isLive = false
                visited[pos.i][pos.j] = shark.index
            } else {
                shark.isLive = false
            }
        }
    }

    map = Array(r + 1) { IntArray(c + 1) { EMPTY } }
    sharkList.forEach { shark ->
        if (shark.isLive) {
            val pos = shark.pos
            map[pos.i][pos.j] = shark.index
        }
    }
}

private fun move(shark: Shark) {
    val pos = shark.pos

    when (shark.d) {
        UP -> pos.i -= shark.s
        DOWN -> pos.i += shark.s
        RIGHT -> pos.j += shark.s
        LEFT -> pos.j -= shark.s
    }

    check(shark)
}

private fun check(shark: Shark) {
    val pos = shark.pos

    while (!isInRange(pos.i, pos.j)) {
        when (shark.d) {
            UP -> {
                pos.i = 1 + (1 - pos.i)
                shark.d = DOWN
            }

            DOWN -> {
                pos.i = r - (pos.i - r)
                shark.d = UP
            }

            RIGHT -> {
                pos.j = c - (pos.j - c)
                shark.d = LEFT
            }

            LEFT -> {
                pos.j = 1 + (1 - pos.j)
                shark.d = RIGHT
            }
        }
    }
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 1..r && j in 1..c
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
