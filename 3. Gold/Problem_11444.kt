/*
* 백준 11444번. 피보나치 수 6
* https://www.acmicpc.net/problem/11444
*/

private val originMatrix = arrayOf(arrayOf(1L, 1L), arrayOf(1L, 0L))
private const val MOD = 1000000007

fun main() {

    val num = initVariable()
    val result = getResult(num)
    printResult(result)
}


fun initVariable(): Long {

    val br = System.`in`.bufferedReader()
    return br.readLine().toLong()
}

fun getResult(num: Long): Long {

    /*
		 *                n
		 *       | 1   1 |    | F(n+1)  F(n)  |
		 * A^n = |       |  = |               |
		 *       | 1   0 |    |  F(n)  F(n-1) |
		 *
     */

    return powerMatrix(originMatrix, num - 1)[0][0]
}

fun powerMatrix(matrix: Array<Array<Long>>, exponent: Long): Array<Array<Long>> {

    if (exponent == 1L || exponent == 0L) return matrix

    val halfExponent = powerMatrix(matrix, exponent / 2)

    return if (exponent % 2 == 1L) {
        multipleMatrix(multipleMatrix(halfExponent, halfExponent), originMatrix)
    } else {
        multipleMatrix(halfExponent, halfExponent)
    }
}

fun multipleMatrix(matrixA: Array<Array<Long>>, matrixB: Array<Array<Long>>): Array<Array<Long>> {

    val result = Array(size = matrixA.size) { Array(size = matrixB[0].size) { 0L } }

    for (i in matrixA.indices) {
        for (j in matrixB[0].indices) {
            for (k in matrixA[i].indices) {
                result[i][j] += matrixA[i][k] * matrixB[k][j]
            }
            result[i][j] = result[i][j] % MOD
        }
    }

    return result
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
