/*
* 백준 9328번. 열쇠
* https://www.acmicpc.net/problem/9328
*/

data class Position(val i: Int, val j: Int)

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private const val EMPTY = '.'
private const val WALL = '*'
private const val DOCUMENT = '$'

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = arrayListOf<Int>()

    val tc = br.readLine().toInt()
    repeat(tc) {
        val (h, w) = br.readLine().split(' ').map { it.toInt() }
        val map = Array(h + 2) { CharArray(w + 2) { EMPTY } }

        for (i in 1 until map.size - 1) {
            val line = br.readLine()
            for (j in 0 until w) {
                map[i][j + 1] = line[j]
            }
        }

        val key = BooleanArray(26) { false }
        br.readLine().forEach {
            if (it != '0') {
                key[it - 'a'] = true
            }
        }

        result.add(getResult(map, key))
    }

    br.close()

    printResult(result)
}

private fun getResult(map: Array<CharArray>, key: BooleanArray): Int {
    var result = 0
    val visited = Array(map.size) { BooleanArray(map[0].size) { false } }
    val visitedLockedDoor = Array(26) { arrayListOf<Position>() }

    val queue = ArrayDeque<Position>()
    queue.add(Position(0, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (k in 0 until 4) {
            val ni = cur.i + di[k]
            val nj = cur.j + dj[k]

            if (isInRange(map, ni, nj) && !visited[ni][nj] && map[ni][nj] != WALL) {
                when (map[ni][nj]) {
                    EMPTY -> queue.add(Position(ni, nj))
                    DOCUMENT -> {
                        result++
                        queue.add(Position(ni, nj))
                    }

                    in 'A'..'Z' -> {
                        if (key[map[ni][nj] - 'A']) {
                            queue.add(Position(ni, nj))
                        } else {
                            visitedLockedDoor[map[ni][nj] - 'A'].add(Position(ni, nj))
                        }
                    }

                    in 'a'..'z' -> {
                        key[map[ni][nj] - 'a'] = true
                        queue.add(Position(ni, nj))

                        while (visitedLockedDoor[map[ni][nj] - 'a'].isNotEmpty()) {
                            val door = visitedLockedDoor[map[ni][nj] - 'a'].removeFirst()
                            queue.add(door)
                        }
                    }
                }

                visited[ni][nj] = true
            }
        }
    }

    return result
}

private fun isInRange(map: Array<CharArray>, i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
