/*
* 백준 27453번. 귀엽기만 한 게 아닌 한별 양
* https://www.acmicpc.net/problem/27453
*/

data class Position(
    val i: Int,
    val j: Int,
    val came: Int, // 지난번 위치에서 어떤 방향으로 왔는가?
    val time: Int, // 이 위치에 도달하기 위해 얼마큼 움직였는가
)

private var row = -1
private var column = -1
private lateinit var town: Array<CharArray>

private var maxScandal = -1 // 막을 수 있는 불상사의 개수

private const val START = 'S'
private const val HOME = 'H'
private const val WALL = 'X'

private lateinit var start: Position

private val di = arrayOf(-1, 0, 0, 1) // 상, 좌, 우, 하
private val dj = arrayOf(0, -1, 1, 0)

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
        maxScandal = it[2]
    }

    town = Array(row) { CharArray(column) }

    for (i in 0 until row) {
        val line = br.readLine()

        for (j in 0 until column) {
            town[i][j] = line[j]

            if (town[i][j] == START) {
                start = Position(i, j, -1, 0)
                town[i][j] = '0'
            }
        }
    }

    br.close()
}

private fun getResult(): Int {
    val visited = Array(row) { Array(column) { BooleanArray(4) { false } } }
    val queue = ArrayDeque<Position>()
    queue.add(start)

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (dir in 0 until 4) {
            if (dir == cur.came) continue // 이전 지역으로 돌아가려고 한다면

            val ni = cur.i + di[dir]
            val nj = cur.j + dj[dir]

            if (!canGo(ni, nj)) continue // 갈 수 없는 경우
            if (isHome(ni, nj)) return cur.time + 1 // 집에 도착한 경우

            val came = reverse(dir) // [ni][nj]가 [cur.i][cur.j]에서 어떤 방향으로 왔는지
            if (scandalSum(cur, dir) > maxScandal || visited[ni][nj][came]) continue

            visited[ni][nj][came] = true
            queue.add(Position(ni, nj, came, cur.time + 1))
        }
    }

    return -1
}

private fun isHome(i: Int, j: Int): Boolean {
    return town[i][j] == HOME
}

private fun canGo(i: Int, j: Int): Boolean {
    return isInRange(i, j) && town[i][j] != WALL
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until row && j in 0 until column
}

private fun scandalSum(cur: Position, dir: Int): Int {
    val beforeScandal = if (cur.came in 0..3) scandal(cur.i + di[cur.came], cur.j + dj[cur.came]) else 0
    val currentScandal = scandal(cur.i, cur.j)
    val futureScandal = scandal(cur.i + di[dir], cur.j + dj[dir])

    return beforeScandal + currentScandal + futureScandal
}

private fun reverse(dir: Int): Int {
    return 3 - dir
}

private fun scandal(i: Int, j: Int): Int {
    return town[i][j] - '0'
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}