/*
* 백준 2206번. 벽 부수고 이동하기
* https://www.acmicpc.net/problem/2206
*/

data class Position(val i: Int, val j: Int, val destroyed: Boolean)

private lateinit var map: Array<IntArray>
private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    map = Array(size = n) { IntArray(size = m) { -1 } }
    for (i in 0 until n) {

        val line = br.readLine()
        for (j in 0 until m) {
            map[i][j] = line[j] - '0'
        }
    }
}

fun getResult(): Int {

    val visited = Array(size = map.size) { Array(size = map.first().size) { BooleanArray(size = 2) { false } } }
    val distance = Array(size = map.size) { IntArray(size = map.first().size) { -1 } }

    val queue = ArrayDeque<Position>()
    queue.add(Position(0, 0, false))
    visited[0][0][0] = true
    distance[0][0] = 1

    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()

        for (k in 0 until 4) {

            val ni = top.i + di[k]
            val nj = top.j + dj[k]

            if (isInRange(ni, nj)) {

                // 벽이 있고, 기존에 부순 적이 없고, 벽을 부수고 이동했을 때 방문 안 한 경우
                if (map[ni][nj] == 1 && !top.destroyed && !visited[ni][nj][1]) {
                    visited[ni][nj][1] = true
                    distance[ni][nj] = distance[top.i][top.j] + 1
                    queue.add(Position(ni, nj, true))
                }

                // 벽이 아니고 방문한 적이 없는 경우
                if (map[ni][nj] == 0 && !visited[ni][nj][top.destroyed.toInt()]) {
                    visited[ni][nj][top.destroyed.toInt()] = true
                    distance[ni][nj] = distance[top.i][top.j] + 1
                    queue.add(Position(ni, nj, top.destroyed))
                }

                // 도착지
                if(ni == map.size - 1 && nj == map.first().size - 1) {
                    return distance[ni][nj]
                }
            }
        }
    }

    return if(map.size == 1 && map.first().size == 1) {
        1
    } else {
        -1
    }
}

fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

fun Boolean.toInt() = if(this) 1 else 0

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
