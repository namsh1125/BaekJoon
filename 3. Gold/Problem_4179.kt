import kotlin.collections.ArrayDeque

/*
* 백준 4179번. 불!
* https://www.acmicpc.net/problem/4179
*/

data class JihunPos(val i: Int, val j: Int, val time: Int)
data class FirePos(val i: Int, val j: Int)

private var row = -1
private var column = -1
private lateinit var map: Array<CharArray>
private lateinit var start: JihunPos
private var firePos = ArrayDeque<FirePos>()

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private const val WALL = '#'
private const val EMPTY = '.'
private const val START = 'J'
private const val FIRE = 'F'

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(' ').map { it.toInt() }.also {
        row = it[0]
        column = it[1]
    }

    map = Array(row) { CharArray(column) }
    for (i in 0 until row) {
        val line = br.readLine()
        for (j in 0 until column) {
            map[i][j] = line[j]

            when (map[i][j]) {
                START -> start = JihunPos(i, j, 0)
                FIRE -> firePos.add(FirePos(i, j))
            }
        }
    }

    br.close()
}

private fun getResult(): String {
    val visited = Array(row) { BooleanArray(column) { false } }
    visited[start.i][start.j] = true

    val queue = ArrayDeque<JihunPos>()
    queue.add(start)

    var currentTime = -1
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        if (escape(cur.i, cur.j)) return "${cur.time + 1}"

        if (currentTime != cur.time) {
            fireSpreads()
            currentTime = cur.time
        }

        for (k in 0 until 4) {
            val ni = cur.i + di[k]
            val nj = cur.j + dj[k]

            if (isInRange(ni, nj) && !visited[ni][nj] && map[ni][nj] == EMPTY) {
                queue.add(JihunPos(ni, nj, cur.time + 1))
                visited[ni][nj] = true
            }
        }
    }

    return "IMPOSSIBLE"
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until row && j in 0 until column
}

private fun escape(i: Int, j: Int): Boolean {
    return i == 0 || i == row - 1 || j == 0 || j == column - 1
}

private fun fireSpreads() {
    val spread = ArrayDeque<FirePos>()

    while (firePos.isNotEmpty()) {
        val cur = firePos.removeFirst()

        for (k in 0 until 4) {
            val ni = cur.i + di[k]
            val nj = cur.j + dj[k]

            if (isInRange(ni, nj) && map[ni][nj] == EMPTY) {
                map[ni][nj] = FIRE
                spread.add(FirePos(ni, nj))
            }
        }
    }

    firePos = spread
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}