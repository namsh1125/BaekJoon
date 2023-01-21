import kotlin.math.min

/*
* 백준 11049번. 행렬 곱셈 순서
* https://www.acmicpc.net/problem/11049
*/

data class Matrix(val row: Int, val column: Int)

const val INF = 999_999_999_999

private fun main() {
    val matrixList = initVariable()
    val result = getResult(matrixList)
    printResult(result)
}

private fun initVariable(): ArrayDeque<Matrix> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val matrixList = ArrayDeque<Matrix>()
    repeat(n) {
        val (row, column) = br.readLine().split(' ').map { it.toInt() }
        matrixList.add(Matrix(row, column))
    }

    return matrixList
}

private fun getResult(matrixList: ArrayDeque<Matrix>): Long {

    val dp = Array(size = matrixList.size) { LongArray(size = matrixList.size) { 0 } }

    for (range in 1 until dp.size) {
        for (i in 0 until dp.size - range) {
            dp[i][i + range] = INF

            for (j in i until i + range) {
                dp[i][i + range] = min(dp[i][i + range], dp[i][j] + dp[j + 1][i + range] + multiplication(i, j, i + range, matrixList))
            }
        }
    }

    return dp[0][dp.size - 1]
}

private fun multiplication(i: Int, j: Int, k: Int, matrixList: ArrayDeque<Matrix>): Int {
    return matrixList[i].row * matrixList[j].column * matrixList[k].column
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
