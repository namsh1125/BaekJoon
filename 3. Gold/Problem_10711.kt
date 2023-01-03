import javafx.geometry.Pos
import java.util.StringTokenizer
import kotlin.math.pow

/*
* 백준 10711번. 모래성
* https://www.acmicpc.net/problem/10711
*/

data class Position(val i: Int, val j: Int)

private lateinit var sandCastle: Array<IntArray>
private val noPower = ArrayDeque<Position>()

val dx = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
val dy = arrayOf(1, 0, -1, 1, -1, 1, 0, -1)

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (h, w) = br.readLine().split(' ').map { it.toInt() }
    sandCastle = Array(size = h) { IntArray(size = w) { -1 } }

    for (i in sandCastle.indices) {

        val line = br.readLine()
        for (j in sandCastle[i].indices) {
            when (line[j]) {
                '.' -> {
                    sandCastle[i][j] = 0
                    noPower.add(Position(i, j))
                }

                else -> sandCastle[i][j] = line[j] - '0'
            }
        }
    }
}

fun getResult(): Int {

    var result = 0
    while (true) {
        for (k in 0 until noPower.size) {

            val position = noPower.removeFirst()

            for (l in 0 until 8) {
                val ni = position.i + dx[l]
                val nj = position.j + dy[l]

                if (isInRange(ni, nj) && sandCastle[ni][nj] > 0) {
                    sandCastle[ni][nj]--

                    if (sandCastle[ni][nj] == 0) {
                        noPower.add(Position(ni, nj))
                    }
                }
            }
        }

        if (noPower.isEmpty()) break
        else result++
    }

    return result
}

fun isInRange(i: Int, j: Int): Boolean {
    return (i in sandCastle.indices) && (j in sandCastle[i].indices)
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
