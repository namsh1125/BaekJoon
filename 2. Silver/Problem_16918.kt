/*
* 백준 16918번. 봄버맨
* https://www.acmicpc.net/problem/16918
*/

private lateinit var board: Array<CharArray>
private lateinit var bombTime: Array<IntArray>
private var time = 0

private var dx = arrayOf(1, -1, 0, 0)
private var dy = arrayOf(0, 0, 1, -1)

fun main() {

    initVariable()
    simulation()
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (r, c, n) = br.readLine().split(' ').map { it.toInt() }

    board = Array(size = r) { CharArray(size = c) { ' ' } }
    bombTime = Array(size = r) { IntArray(size = c) { 0 } }
    for (i in board.indices) {

        val line = br.readLine()
        for (j in line.indices) {
            board[i][j] = line[j]
            if (board[i][j] == 'O') bombTime[i][j] = 3
        }
    }

    time = n
}

fun simulation() {

    var currentTime = 0
    while (currentTime++ < time) {

        if (currentTime % 2 == 0) setBombs(currentTime)
        else explodeBombs(currentTime)
    }
}

fun setBombs(currentTime: Int) {

    for (i in board.indices) {
        for (j in board[i].indices) {
            if (board[i][j] == '.') {
                board[i][j] = 'O'
                bombTime[i][j] = currentTime + 3
            }
        }
    }
}

fun explodeBombs(currentTime: Int) {

    for (i in board.indices) {
        for (j in board[i].indices) {

            if (bombTime[i][j] == currentTime) {
                board[i][j] = '.'

                for (k in 0 until 4) {
                    val ni = i + dx[k]
                    val nj = j + dy[k]

                    if (!isInRange(ni, nj)) continue

                    if (board[ni][nj] == 'O' && bombTime[ni][nj] != currentTime) {
                        board[ni][nj] = '.'
                        bombTime[ni][nj] = 0
                    }
                }
            }
        }
    }
}

fun isInRange(i: Int, j: Int): Boolean {
    return (i in board.indices && j in board[i].indices)
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    for (i in board.indices) {
        for (j in board[i].indices) {
            bw.write("${board[i][j]}")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}
