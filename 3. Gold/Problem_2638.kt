/*
* 백준 2638번. 치즈
* https://www.acmicpc.net/problem/2638
*/

data class Position(val i: Int, val j: Int)

private lateinit var map: Array<IntArray>
private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private const val CHEESE = 1
private const val OUT = 3

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    map = Array(n) { IntArray(m) { -1 } }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until m) {
            map[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    var time = 0
    while (cheeseExist()) {
        updateOutside()
        melt()
        time++
    }

    return time
}

private fun updateOutside() {
    val visited = Array(map.size) { BooleanArray(map.first().size) { false } }

    val queue = ArrayDeque<Position>()
    queue.add(Position(0, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (k in 0 until 4) {
            val ni = cur.i + di[k]
            val nj = cur.j + dj[k]

            if (isInRange(ni, nj) && !visited[ni][nj] && map[ni][nj] != CHEESE) {
                map[ni][nj] = OUT
                visited[ni][nj] = true
                queue.add(Position(ni, nj))
            }
        }
    }
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun melt() {
    val meltList = ArrayDeque<Position>()

    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == CHEESE) {
                var count = 0

                for (k in 0 until 4) {
                    val ni = i + di[k]
                    val nj = j + dj[k]

                    if (isInRange(ni, nj) && map[ni][nj] == OUT) count++
                }

                if (count >= 2) meltList.add(Position(i, j))
            }
        }
    }

    while (meltList.isNotEmpty()) {
        val cur = meltList.removeFirst()
        map[cur.i][cur.j] = OUT
    }
}

private fun cheeseExist(): Boolean {
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == CHEESE) return true
        }
    }
    return false
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
