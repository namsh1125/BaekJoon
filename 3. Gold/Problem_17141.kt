import kotlin.math.max
import kotlin.math.min

/*
* 백준 17141번. 연구소 2
* https://www.acmicpc.net/problem/17141
*/

data class Position(val i: Int, val j: Int, val time: Int)

private var n = -1
private lateinit var map: Array<IntArray>

private var m = -1
private val availableVirusPositionList = ArrayDeque<Position>()

private const val EMPTY = 0
private const val WALL = 1
private const val VIRUSAVAILABLE = 2
private const val VIRUS = 2

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
    }

    map = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until n) {
            map[i][j] = line[j]

            if (map[i][j] == VIRUSAVAILABLE) {
                availableVirusPositionList.add(Position(i, j, 0))
            }
        }
    }

    br.close()
}

private fun getResult(): Int {
    var result = Int.MAX_VALUE
    val selected = BooleanArray(availableVirusPositionList.size) { false }

    fun choose(index: Int, count: Int) {
        if (count == m) {
            result = min(result, spreadVirus(selected))
            return
        }

        if (index == availableVirusPositionList.size) return

        selected[index] = true
        choose(index + 1, count + 1)

        selected[index] = false
        choose(index + 1, count)
    }

    choose(0, 0)

    return if (result == Int.MAX_VALUE) -1 else result
}


private fun spreadVirus(selected: BooleanArray): Int {
    val map = copyMap()
    val visited = Array(n) { BooleanArray(n) { false } }
    val queue = ArrayDeque<Position>()

    for (i in selected.indices) {
        if (selected[i]) {
            val pos = availableVirusPositionList[i]
            visited[pos.i][pos.j] = true
            queue.add(pos)
        }
    }

    var time = 0
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        time = max(time, cur.time)

        for (k in 0 until 4) {
            val ni = cur.i + di[k]
            val nj = cur.j + dj[k]

            if (isInRange(ni, nj) && map[ni][nj] != WALL && !visited[ni][nj]) {
                map[ni][nj] = VIRUS
                visited[ni][nj] = true
                queue.add(Position(ni, nj, cur.time + 1))
            }
        }
    }

    return if (spreadAllOver(map)) time else Int.MAX_VALUE
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until n && j in 0 until n
}

private fun spreadAllOver(map: Array<IntArray>): Boolean {
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[i][j] == EMPTY) return false
        }
    }

    return true
}

private fun copyMap(): Array<IntArray> {
    val copy = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            copy[i][j] = map[i][j]
        }
    }

    return copy
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
