import kotlin.system.exitProcess

/*
* 백준 2239번. 스도쿠
* https://www.acmicpc.net/problem/2239
*/

private const val BLANK = 0
private val board = Array(9) { IntArray(9) { BLANK } }

private fun main() {
    initVariable()
    solve()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    for (i in board.indices) {
        val line = br.readLine()
        for (j in board[i].indices) {
            board[i][j] = line[j] - '0'
        }
    }

    br.close()
}

private fun solve() {
    fillSudoku(0, 0)
}

private fun fillSudoku(row: Int, column: Int) {
    if (column == 9) {
        fillSudoku(row + 1, 0)
        return
    }

    if (row == 9) {
        printResult()
        exitProcess(0)
    }

    if (board[row][column] == BLANK) {
        for (num in 1..9) {
            if (possibility(row, column, num)) {
                board[row][column] = num
                fillSudoku(row, column + 1)
            }
        }
        board[row][column] = BLANK
        return
    }

    fillSudoku(row, column + 1)
}

private fun possibility(row: Int, column: Int, value: Int): Boolean {
    for (i in 0 until 9) {
        if (board[i][column] == value) return false
    }

    for (j in 0 until 9) {
        if (board[row][j] == value) return false
    }

    val areaRow = (row / 3) * 3
    val areaColumn = (column / 3) * 3
    for (i in areaRow until areaRow + 3) {
        for (j in areaColumn until areaColumn + 3) {
            if (board[i][j] == value) return false
        }
    }

    return true
}

private fun printResult() {
    val bw = System.out.bufferedWriter()

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            bw.write("${board[i][j]}")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}
