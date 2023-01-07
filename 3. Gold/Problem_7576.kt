import java.lang.Integer.max
import java.util.StringTokenizer

/*
* 백준 7576번. 토마토
* https://www.acmicpc.net/problem/7576
*/

data class Tomato(val i: Int, val j: Int, val time: Int)

private lateinit var box: Array<IntArray>
private val queue = ArrayDeque<Tomato>()

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (m, n) = br.readLine().split(' ').map { it.toInt() }

    box = Array(size = n) { IntArray(size = m) { 0 } }
    for (i in box.indices) {

        val st = StringTokenizer(br.readLine())
        for (j in box[i].indices) {
            box[i][j] = st.nextToken().toInt()

            if (box[i][j] == 1) {
                queue.add(Tomato(i, j, 0))
            }
        }
    }
}

fun getResult(): Int {

    var result = 0
    while (queue.isNotEmpty()) {

        val tomato = queue.removeFirst()
        for (k in 0 until 4) {

            val ni = tomato.i + di[k]
            val nj = tomato.j + dj[k]

            if (isInRange(ni, nj) && box[ni][nj] == 0) {
                queue.add(Tomato(ni, nj, tomato.time + 1))
                box[ni][nj] = 1
                result = max(result, tomato.time + 1)
            }
        }
    }

    for (i in box.indices) {
        for (j in box[i].indices) {
            if (box[i][j] == 0) return -1
        }
    }

    return result
}

fun isInRange(i: Int, j: Int): Boolean {
    return i in box.indices && j in box[i].indices
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
