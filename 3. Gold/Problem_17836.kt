import java.lang.Integer.min
import java.util.PriorityQueue
import kotlin.math.abs

/*
* 백준 17836번. 공주님을 구해라!
* https://www.acmicpc.net/problem/17836
*/

data class Position(
        val i: Int,
        val j: Int,
        val time: Int,
        var haveGram: Boolean
) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        return time - other.time
    }
}

private var n = -1
private var m = -1
private lateinit var map: Array<IntArray>

private var t = -1

private const val EMPTY = 0
private const val WALL = 1
private const val GRAM = 2

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

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
        t = it[2]
    }

    map = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until m) {
            map[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): String {
    val result = meetPrincess()
    return if (result <= t) result.toString() else "Fail"
}

private fun meetPrincess(): Int {
    var result = Int.MAX_VALUE // 그람을 만나고, 공주에게 바로 가는 시간

    var visited = Array(n) { BooleanArray(m) { false } }
    visited[0][0] = true

    val queue = PriorityQueue<Position>()
    queue.add(Position(0, 0, 0, false))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        if (map[cur.i][cur.j] == GRAM) {
            cur.haveGram = true
            result = cur.time + abs((n - 1) - cur.i) + abs((m - 1) - cur.j)
        }

        for (k in 0 until 4) {
            val ni = cur.i + di[k]
            val nj = cur.j + dj[k]

            if (isInRange(ni, nj) && !visited[ni][nj]) {
                if (map[ni][nj] == WALL && !cur.haveGram) continue
                if (ni == n - 1 && nj == m - 1) return min(cur.time + 1, result)

                visited[ni][nj] = true
                queue.add(Position(ni, nj, cur.time + 1, cur.haveGram))
            }
        }
    }

    return result
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until n && j in 0 until m
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
