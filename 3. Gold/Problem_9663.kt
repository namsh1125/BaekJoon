import kotlin.math.abs

/*
* 백준 9663번. N-Queen
* https://www.acmicpc.net/problem/9663
*/

private var n = -1
private val board = IntArray(size = 15) { 0 }
private var result = 0

fun main() {

    initVariable()
    nQueen(0)
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
}

fun nQueen(i: Int) {

    if (i == n) {
        result++
        return
    }

    for (j in 0 until n) {
        board[i] = j
        if (isAvailable(i)) {
            nQueen(i + 1)
        }
    }
}

fun isAvailable(i: Int): Boolean {

    for (j in 0 until i) {
        if (board[i] == board[j] || abs(i - j) == abs(board[i] - board[j])) return false
    }

    return true
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
