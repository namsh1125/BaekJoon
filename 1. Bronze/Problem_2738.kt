/*
* 백준 2738번. 행렬 덧셈
* https://www.acmicpc.net/problem/2738
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    val result = Array(n) { IntArray(m) { 0 } }

    repeat(2) {
        for (i in result.indices) {
            val line = br.readLine().split(' ').map { it.toInt() }
            for (j in result[i].indices) {
                result[i][j] += line[j]
            }
        }
    }

    printResult(result)
}

private fun printResult(result: Array<IntArray>) {
    val bw = System.out.bufferedWriter()
    for (i in result.indices) {
        for (j in result[i].indices) {
            bw.write("${result[i][j]} ")
        }
        bw.write("\n")
    }
    bw.flush()
    bw.close()
}
