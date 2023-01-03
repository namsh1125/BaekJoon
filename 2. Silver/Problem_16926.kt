import java.lang.Integer.min
import java.util.*

/*
* 백준 16926번. 배열 돌리기 1
* https://www.acmicpc.net/problem/16926
*/

private lateinit var arr: Array<IntArray>
private var rotate = -1

fun main() {

    initVariable()
    val result = rotateArray()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m, r) = br.readLine().split(' ').map { it.toInt() }

    arr = Array(size = n) { IntArray(size = m) { 0 } }
    for (i in arr.indices) {

        val st = StringTokenizer(br.readLine())
        for (j in arr[i].indices) {
            arr[i][j] = st.nextToken().toInt()
        }
    }

    rotate = r
}

fun rotateArray(): Array<IntArray> {

    var result = arr

    for (i in 0 until rotate) {
        result = rotateArray(result)
    }

    return result
}

fun rotateArray(arr: Array<IntArray>): Array<IntArray> {

    val result = Array(size = arr.size) { IntArray(size = arr[0].size) }
    for (i in 0 until min(arr.size, arr[0].size) / 2) {

        val iMax = arr.size - i - 1
        val jMax = arr[i].size - i - 1

        // 상단 (우 -> 왼)
        for (j in i until jMax) result[i][j] = arr[i][j + 1]

        // 좌측 (위 -> 아래)
        for (j in i + 1..iMax) result[j][i] = arr[j - 1][i]

        // 하단 (왼 -> 우)
        for (j in i + 1..jMax) result[iMax][j] = arr[iMax][j - 1]

        // 우측 (아래 -> 위)
        for (j in i until iMax) result[j][jMax] = arr[j + 1][jMax]

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
