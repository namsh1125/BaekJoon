import java.lang.Integer.min
import java.util.*

/*
* 백준 1018번. 체스판 다시 칠하기
* https://www.acmicpc.net/problem/1018
*/

private lateinit var chess: Array<Array<Boolean>>

fun main() {

    initVariable()
    val result = findResult()
    printResult(result)
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, m) = nextLine().split(' ').map { it.toInt() }
    chess = Array(size = n) { Array(size = m) { false } }

    for (i in 0 until n) {

        val line = nextLine().toCharArray()

        for (j in 0 until m) {

            when (line[j]) {
                'W' -> chess[i][j] = true
                else -> chess[i][j] = false
            }
        }
    }
}

fun findResult(): Int {

    var result = 64

    for (i in 0..chess.size - 8) {
        for (j in 0..chess[i].size - 8) {

            val repaint = findRepaint(i, j)
            result = min(result, repaint)
            result = min(result, 64-repaint) // 위와 다른 색상으로 색칠하는 경우
        }
    }

    return result
}

fun findRepaint(i: Int, j: Int): Int {

    var color: Boolean = chess[i][j]
    var result = 0

    for (a in i until i + 8) {
        for (b in j until j + 8) {

            if (chess[a][b] != color) result++
            color = !color
        }
        color = !color
    }

    return result
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
