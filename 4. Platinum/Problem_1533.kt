/*
* 백준 1533번. 길의 개수
* https://www.acmicpc.net/problem/1533
*/

private lateinit var matrix: Array<LongArray>
private var start = -1
private var end = -1
private var late = -1

private const val MOD = 1_000_003L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val line = br.readLine().split(' ').map { it.toInt() }
    val n = line[0]
    start = line[1] - 1
    end = line[2] - 1
    late = line[3]

    matrix = Array(n * 5) { LongArray(n * 5) { 0 } }
    for (i in 0 until n) {
        for (j in 1 until 5) {
            matrix[i * 5 + j][i * 5 + j - 1] = 1
        }
    }

    for (i in 0 until n) {
        val line = br.readLine()
        for (j in 0 until n) {
            val dis = line[j] - '0'
            if (dis > 0) matrix[i * 5][j * 5 + dis - 1] = 1
        }
    }

    br.close()
}

private fun getResult(): Long {
    return powerMatrix(matrix, late)[start * 5][end * 5]
}

private fun powerMatrix(matrix: Array<LongArray>, time: Int): Array<LongArray> {
    return if (time == 1) matrix
    else if (time % 2 == 0) {
        val mid = powerMatrix(matrix, time / 2)
        multipleMatrix(mid, mid)
    } else {
        multipleMatrix(matrix, powerMatrix(matrix, time - 1))
    }
}

private fun multipleMatrix(matrix1: Array<LongArray>, matrix2: Array<LongArray>): Array<LongArray> {
    val n = matrix1.size
    val m = matrix1.first().size
    val r = matrix2.first().size

    val newMatrix = Array(n) { LongArray(r) { 0 } }
    for (i in 0 until n) {
        for (j in 0 until r) {
            for (k in 0 until m) {
                newMatrix[i][j] += matrix1[i][k] * matrix2[k][j]
                newMatrix[i][j] %= MOD
            }
        }
    }

    return newMatrix
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
