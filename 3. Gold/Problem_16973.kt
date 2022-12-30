import java.lang.Integer.max

/*
* 백준 16973번. 직사각형 탈출
* https://www.acmicpc.net/problem/16973
*/

data class Info(val i: Int, val j: Int, val count: Int)

private var dx = arrayOf(1, -1, 0, 0)
private var dy = arrayOf(0, 0, 1, -1)

private lateinit var board: Array<ArrayList<Int>>
private lateinit var visited: Array<BooleanArray>
private var h = 0 // 직사각형 height
private var w = 0 // 직사각형 width
private var sr = 0 // 직사각형 시작 좌표 r
private var sc = 0 // 직사각형 시작 좌표 c
private var fr = 0 // 직사각형 도착 좌표 r
private var fc = 0 // 직사각형 도착 좌표 c

fun main() {

    initVariable()
    makeWall()
    val result = bfs()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine()!!.split(' ').map { it.toInt() }

    board = Array(size = n) { arrayListOf() }
    visited = Array(size = n) { BooleanArray(size = m) { false } }

    for (i in 0 until n) {

        val line = br.readLine()!!.split(' ').map { it.toInt() }
        board[i] = line as ArrayList<Int>
    }

    val rectangleInfo = br.readLine()!!.split(' ').map { it.toInt() }
    h = rectangleInfo[0]
    w = rectangleInfo[1]
    sr = rectangleInfo[2] - 1
    sc = rectangleInfo[3] - 1
    fr = rectangleInfo[4] - 1
    fc = rectangleInfo[5] - 1
}

fun makeWall() {

    for(i in board.indices) {
        for(j in board[i].indices) {

            if(board[i][j] == 1) {
                for(a in i downTo max(0, i - h + 1)) {
                    for(b in j downTo max(0, j - w + 1)) {
                        board[a][b] = 1
                    }
                }
            }
        }
    }

}

fun bfs(): Int {

    val queue = ArrayDeque<Info>()
    queue.add(Info(sr, sc, 0))
    visited[sr][sc] = true

    while (queue.isNotEmpty()) {

        val info = queue.removeFirst()

        if (info.i == fr && info.j == fc) return info.count

        for (i in 0 until 4) {

            val ni = info.i + dx[i]
            val nj = info.j + dy[i]

            if (isInRange(ni, nj) && board[ni][nj] == 0 && !visited[ni][nj]) {
                queue.add(Info(ni, nj, info.count + 1))
                visited[ni][nj] = true
            }
        }
    }

    return -1
}

fun isInRange(i: Int, j: Int): Boolean {
    return 0 <= i && i + h - 1 < board.size && 0 <= j && j + w - 1 < board[0].size
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
