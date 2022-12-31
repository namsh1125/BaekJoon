import java.util.StringTokenizer

/*
* 백준 17829번. 222-풀링
* https://www.acmicpc.net/problem/17829
*/

fun main() {

    var matrix = initVariable()
    while(matrix.size != 1) {
        matrix = reduction(matrix)
    }
    printResult(matrix[0][0])
}

fun initVariable(): Array<IntArray> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine()!!.toInt()
    val matrix = Array(size = n) { IntArray(size = n) { 0 } }

    for (i in 0 until n) {

        val line = StringTokenizer(br.readLine())
        for (j in 0 until n) {
            matrix[i][j] = line.nextToken().toInt()
        }
    }

    return matrix
}

fun reduction(matrix: Array<IntArray>): Array<IntArray> {

    val newMatrixSize = matrix.size / 2
    val newMatrix = Array(size = newMatrixSize) { IntArray(size = newMatrixSize) { 0 } }

    for (i in matrix.indices step (2)) {
        for (j in matrix.indices step (2)) {

            val num = arrayListOf(matrix[i][j], matrix[i][j + 1], matrix[i + 1][j], matrix[i + 1][j + 1])
            num.sortDescending()
            newMatrix[i / 2][j / 2] = num[1]
        }
    }

    return newMatrix
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
