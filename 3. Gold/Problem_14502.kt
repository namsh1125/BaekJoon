import kotlin.math.max

/*
* 백준 14502번. 연구소
* https://www.acmicpc.net/problem/14502
*/

data class Position(val i: Int, val j: Int)

private lateinit var map: Array<IntArray>
private val blankList = ArrayDeque<Position>()

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private const val BLANK = 0
private const val WALL = 1
private const val VIRUS = 2

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (r, c) = br.readLine().split(' ').map { it.toInt() }

    map = Array(r) { IntArray(c) { -1 } }
    for (i in map.indices) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in map[i].indices) {
            map[i][j] = line[j]

            if (map[i][j] == BLANK) {
                blankList.add(Position(i, j))
            }
        }
    }

    br.close()
}

private fun getResult(): Int {
    var result = 0
    val selected = BooleanArray(blankList.size) { false }

    fun search(index: Int, count: Int) {
        if (count == 3) {
            val newMap = buildWall(selected)
            val infected = infection(newMap)
            result = max(result, getSafeArea(infected))
            return
        }

        if (index == blankList.size) return

        selected[index] = true
        search(index + 1, count + 1)

        selected[index] = false
        search(index + 1, count)
    }
    search(0, 0)

    return result
}

private fun buildWall(selected: BooleanArray): Array<IntArray> {
    val newMap = copyMap()

    for (i in selected.indices) {
        if (selected[i]) {
            val pos = blankList[i]
            newMap[pos.i][pos.j] = WALL
        }
    }

    return newMap
}

private fun copyMap(): Array<IntArray> {
    val newMap = Array(map.size) { IntArray(map[0].size) }

    for (i in map.indices) {
        for (j in map[i].indices) {
            newMap[i][j] = map[i][j]
        }
    }

    return newMap
}

private fun infection(map: Array<IntArray>): Array<IntArray> {
    val virusPosition = ArrayDeque<Position>()

    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == VIRUS) {
                virusPosition.add(Position(i, j))
            }
        }
    }

    val visited = Array(map.size) { BooleanArray(map[0].size) { false } }
    while (virusPosition.isNotEmpty()) {
        val current = virusPosition.removeFirst()
        visited[current.i][current.j] = true

        for (k in 0 until 4) {
            val ni = current.i + di[k]
            val nj = current.j + dj[k]

            if (isInRange(ni, nj) && !visited[ni][nj] && map[ni][nj] == BLANK) {
                map[ni][nj] = VIRUS
                virusPosition.add(Position(ni, nj))
            }
        }
    }

    return map
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun getSafeArea(map: Array<IntArray>): Int {
    var result = 0

    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == BLANK) result++
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
