import java.lang.StringBuilder
import kotlin.math.max
import kotlin.math.min

/*
* 백준 4095번. 최대 정사각형
* https://www.acmicpc.net/problem/4095
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = StringBuilder()

    while (true) {
        val (n, m) = br.readLine().split(' ').map { it.toInt() }
        if (n == 0 && m == 0) break

        val matrix = Array(n + 1) { IntArray(m + 1) { 0 } }

        for (i in 1..n) {
            val line = br.readLine().split(' ').map { it.toInt() }
            for (j in 1..m) {
                matrix[i][j] = line[j - 1]
            }
        }

        result.append("${getResult(matrix, n, m)}\n")
    }
    br.close()

    printResult(result.toString())
}

private fun getResult(matrix: Array<IntArray>, row: Int, column: Int): Int {
    var result = 0

    for (i in 1..row) {
        for (j in 1..column) {
            if (matrix[i][j] != 0) {
                matrix[i][j] = min(matrix[i - 1][j], matrix[i][j - 1], matrix[i - 1][j - 1]) + 1
                result = max(result, matrix[i][j])
            }
        }
    }

    return result
}

private fun min(a: Int, b: Int, c: Int): Int {
    return min(min(a, b), c)
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
