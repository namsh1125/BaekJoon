/*
* 백준 2448번. 별 찍기 - 11
* https://www.acmicpc.net/problem/2448
*/

private lateinit var arr: Array<CharArray>

fun main() {

    initVariable()
    drawTriangle(arr.size, 0, arr[0].size / 2)
    printTriangle()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    arr = Array(size = n) { CharArray(size = 2 * n - 1) { ' ' } }
}

fun drawTriangle(len: Int, row: Int, column: Int) {

    if (len == 3) {
        drawTriangle(row, column)
        return
    }

    drawTriangle(len / 2, row, column)
    drawTriangle(len / 2, row + len / 2, column - len / 2)
    drawTriangle(len / 2, row + len / 2, column + len / 2)
}

fun drawTriangle(row: Int, column: Int) {

    // 첫번째 줄 (*)
    arr[row][column] = '*'

    // 두번쨰 줄 (* *)
    arr[row + 1][column - 1] = '*'
    arr[row + 1][column + 1] = '*'

    // 세번째 줄 (*****)
    arr[row + 2][column - 2] = '*'
    arr[row + 2][column - 1] = '*'
    arr[row + 2][column] = '*'
    arr[row + 2][column + 1] = '*'
    arr[row + 2][column + 2] = '*'
}

fun printTriangle() {

    val bw = System.out.bufferedWriter()
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            bw.write("${arr[i][j]}")
        }
        bw.write("\n")
    }
    bw.flush()
    bw.close()
}
