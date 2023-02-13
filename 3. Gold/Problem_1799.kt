import kotlin.math.max

/*
* 백준 1799번. 비숍
* https://www.acmicpc.net/problem/1799
*/

private var n = -1
private lateinit var board: Array<IntArray>
private val left = BooleanArray(20) { false }
private val right = BooleanArray(20) { false }

private const val BLACK = 0
private const val WHITE = 1
private const val CANPLACE = 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    board = Array(n) { IntArray(n) { -1 } }
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in 0 until n) {
            board[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    val result = IntArray(2) { 0 }

    fun tracking(r: Int, c: Int, count: Int, color: Int) {
        var row = r
        var column = c

        if (column >= n) {
            row++
            column = if (column % 2 == 0) 1 else 0
        }

        if (row >= n) {
            result[color] = max(result[color], count)
            return
        }

        if (board[row][column] == CANPLACE && !left[column - row + n - 1] && !right[row + column]) {
            left[column - row + n - 1] = true
            right[row + column] = true

            tracking(row, column + 2, count + 1, color)

            left[column - row + n - 1] = false
            right[row + column] = false
        }

        tracking(row, column + 2, count, color)
    }

    tracking(0, 0, 0, BLACK)
    tracking(0, 1, 0, WHITE)

    return result.sum()
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
