import java.util.StringTokenizer

/*
* 백준 10830번. 행렬 제곱
* https://www.acmicpc.net/problem/10830
*/

private lateinit var originMatrix: Array<IntArray>
private var time = 0L // 곱하는 횟수
private const val MOD = 1000

fun main() {

    initVariable()
    val result = powerMatrix(originMatrix, time)
    printResult(result)
}


fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, b) = br.readLine().split(' ')

    originMatrix = Array(size = n.toInt()) { IntArray(size = n.toInt()) { 0 } }
    for (i in originMatrix.indices) {

        val st = StringTokenizer(br.readLine())
        for (j in originMatrix[i].indices) {
            originMatrix[i][j] = st.nextToken().toInt() % MOD
        }
    }

    time = b.toLong()
}

fun powerMatrix(matrix: Array<IntArray>, exponent: Long): Array<IntArray> {

    if (exponent == 1L) return matrix

    val halfExponent = powerMatrix(matrix, exponent / 2)

    return if (exponent % 2 == 1L) {
        multipleMatrix(multipleMatrix(halfExponent, halfExponent), originMatrix)
    } else {
        multipleMatrix(halfExponent, halfExponent)
    }
}

fun multipleMatrix(matrixA: Array<IntArray>, matrixB: Array<IntArray>): Array<IntArray> {

    val result = Array(size = matrixA.size) { IntArray(size = matrixB[0].size) { 0 } }

    for(i in matrixA.indices) {
        for(j in matrixB[0].indices) {
            for(k in matrixA[i].indices) {
                result[i][j] += matrixA[i][k] * matrixB[k][j]
            }
            result[i][j] %= MOD
        }
    }

    return result
}

fun printResult(result: Array<IntArray>) {

    val bw = System.out.bufferedWriter()
    for(i in result.indices) {
        for(j in result[i].indices) {
            bw.write("${result[i][j]} ")
        }
        bw.write("\n")
    }
    bw.flush()
    bw.close()
}
