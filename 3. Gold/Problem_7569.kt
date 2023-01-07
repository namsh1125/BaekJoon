import java.lang.Integer.max
import java.util.StringTokenizer

/*
* 백준 7569번. 토마토
* https://www.acmicpc.net/problem/7569
*/

data class Tomato(val i: Int, val j: Int, val k: Int, val time: Int)

private lateinit var box: Array<Array<IntArray>>
private val queue = ArrayDeque<Tomato>()

private val di = arrayOf(1, -1, 0, 0, 0, 0)
private val dj = arrayOf(0, 0, 1, -1, 0, 0)
private val dk = arrayOf(0, 0, 0, 0, 1, -1)

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (m, n, h) = br.readLine().split(' ').map { it.toInt() }

    box = Array(size = h) { Array(size = n) { IntArray(size = m) { 0 } } }
    for (a in box.indices) {
        for (b in box[a].indices) {

            val st = StringTokenizer(br.readLine())
            for (c in box[a][b].indices) {
                box[a][b][c] = st.nextToken().toInt()

                if (box[a][b][c] == 1) {
                    queue.add(Tomato(a, b, c, 0))
                }
            }
        }
    }
}

fun getResult(): Int {

    var result = 0
    while (queue.isNotEmpty()) {

        val tomato = queue.removeFirst()
        for (l in 0 until 6) {

            val ni = tomato.i + di[l]
            val nj = tomato.j + dj[l]
            val nk = tomato.k + dk[l]

            if (isInRange(ni, nj, nk) && box[ni][nj][nk] == 0) {
                queue.add(Tomato(ni, nj, nk, tomato.time + 1))
                box[ni][nj][nk] = 1
                result = max(result, tomato.time + 1)
            }
        }
    }

    for (i in box.indices) {
        for (j in box[i].indices) {
            for (k in box[i][j].indices) {
                if (box[i][j][k] == 0) return -1
            }
        }
    }

    return result
}

fun isInRange(i: Int, j: Int, k: Int): Boolean {
    return i in box.indices && j in box[i].indices && k in box[i][j].indices
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
