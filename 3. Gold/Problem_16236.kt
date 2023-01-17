import java.util.*
import kotlin.collections.ArrayDeque

/*
* 백준 16236번. 아기 상어
* https://www.acmicpc.net/problem/16236
*/

data class Position(
    val i: Int,
    val j: Int
) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        if (this.i > other.i) return 1
        else if (this.i == other.i) {
            if (this.j > other.j) return 1
        }
        return -1
    }
}

private var sharkPosition = Position(-1, -1)

private var di = arrayOf(-1, 0, 0, 1)
private var dj = arrayOf(0, -1, 1, 0)

private fun main() {
    val map = initVariable()
    val result = getResult(map)
    printResult(result)
}

private fun initVariable(): Array<IntArray> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val map = Array(size = n) { IntArray(size = n) { -1 } }

    for (i in map.indices) {

        val st = StringTokenizer(br.readLine())
        for (j in map[i].indices) {
            map[i][j] = st.nextToken().toInt()

            if (map[i][j] == 9) {
                sharkPosition = Position(i, j)
                map[i][j] = 0
            }
        }
    }

    return map
}

private fun getResult(map: Array<IntArray>): Int {

    var sharkSize = 2
    var eat = 0
    var eatFlag = false
    var time = 0
    var result = 0

    var visited = Array(size = map.size) { BooleanArray(size = map.first().size) { false } }
    visited[sharkPosition.i][sharkPosition.j] = true

    val queue = ArrayDeque<Position>()
    queue.add(sharkPosition)

    while (queue.isNotEmpty()) {

        queue.sort()

        for (i in 0 until queue.size) {

            val sharkPosition = queue.removeFirst()
            if (canEat(sharkSize, map[sharkPosition.i][sharkPosition.j])) {
                map[sharkPosition.i][sharkPosition.j] = 0
                eat++

                if (canSizeUp(sharkSize, eat)) {
                    sharkSize++
                    eat = 0
                }

                // 현재 위치 기준으로 먹을 수 있는 물고기를 찾기위해 초기화
                queue.clear()
                visited = Array(size = map.size) { BooleanArray(size = map.first().size) { false } }
                visited[sharkPosition.i][sharkPosition.j] = true
                result = time
                eatFlag = true
            }

            // 현재 위치 기준으로 먹을 수 있는 물고기 찾기
            for (k in 0 until 4) {
                val ni = sharkPosition.i + di[k]
                val nj = sharkPosition.j + dj[k]

                if (isInRange(ni, nj, map) && !visited[ni][nj] && canGo(sharkSize, map[ni][nj])) {
                    queue.add(Position(ni, nj))
                    visited[ni][nj] = true
                }
            }

            if (eatFlag) { // 물고기를 먹었다면
                eatFlag = false
                break
            }
        }
        time++
    }

    return result
}


private fun isInRange(i: Int, j: Int, map: Array<IntArray>): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun canGo(sharkSize: Int, fishSize: Int): Boolean {
    return fishSize <= sharkSize
}

private fun canEat(sharkSize: Int, fishSize: Int): Boolean {
    return fishSize in 1 until sharkSize
}

private fun canSizeUp(sharkSize: Int, eat: Int): Boolean {
    return sharkSize == eat
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
