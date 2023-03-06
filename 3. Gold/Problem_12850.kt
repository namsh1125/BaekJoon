/*
* 백준 12850번. 본대 산책2
* https://www.acmicpc.net/problem/12850
*/

// 0: 정보 과학관, 1: 전산관, 2: 미래관, 3: 신양관, 4: 환경직 기념관, 5: 진리관, 6: 학생 회관, 7: 형남 공학관

private lateinit var graph: Array<Array<Long>>
private const val MOD = 1_000_000_007L
private var d = -1

private fun main() {
    initGraph()
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initGraph() {
    graph = arrayOf(
            arrayOf(0, 1, 1, 0, 0, 0, 0, 0),
            arrayOf(1, 0, 1, 1, 0, 0, 0, 0),
            arrayOf(1, 1, 0, 1, 1, 0, 0, 0),
            arrayOf(0, 1, 1, 0, 1, 1, 0, 0),
            arrayOf(0, 0, 1, 1, 0, 1, 0, 1),
            arrayOf(0, 0, 0, 1, 1, 0, 1, 0),
            arrayOf(0, 0, 0, 0, 0, 1, 0, 1),
            arrayOf(0, 0, 0, 0, 1, 0, 1, 0)
    )
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    d = br.readLine().toInt()
    br.close()
}

private fun getResult(): Long {
    val matrix = multiplication(d)
    return matrix[0][0]
}

private fun multiplication(n: Int): Array<Array<Long>> {
    return if (n == 1) graph
    else if (n and 1 == 1) matrixMultiplication(graph, multiplication(n - 1))
    else {
        val half = multiplication(n / 2)
        matrixMultiplication(half, half)
    }
}

private fun matrixMultiplication(matrixA: Array<Array<Long>>, matrixB: Array<Array<Long>>): Array<Array<Long>> {
    val result = Array(8) { Array(8) { 0L } }

    for (k in 0 until 8) {
        for (i in 0 until 8) {
            for (j in 0 until 8) {
                result[i][j] += matrixA[i][k] * matrixB[k][j]
                result[i][j] %= MOD
            }
        }
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
