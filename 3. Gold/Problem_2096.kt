import kotlin.math.max
import kotlin.math.min

/*
* 백준 2096번. 내려가기
* https://www.acmicpc.net/problem/2096
*/

private lateinit var arr: Array<IntArray>
private lateinit var min: Array<IntArray>
private lateinit var max: Array<IntArray>

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    arr = Array(n) { IntArray(3) { -1 } }
    min = Array(n) { IntArray(3) { 0 } }
    max = Array(n) { IntArray(3) { 0 } }

    for (i in arr.indices) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        arr[i][0] = a
        arr[i][1] = b
        arr[i][2] = c
    }

    br.close()
}

private fun getResult() {
    min[0] = arr[0]
    max[0] = arr[0]

    for (i in 1 until arr.size) {
        min[i][0] = min(min[i - 1][0], min[i - 1][1]) + arr[i][0]
        min[i][1] = min(min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]) + arr[i][1]
        min[i][2] = min(min[i - 1][1], min[i - 1][2]) + arr[i][2]

        max[i][0] = max(max[i - 1][0], max[i - 1][1]) + arr[i][0]
        max[i][1] = max(max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]) + arr[i][1]
        max[i][2] = max(max[i - 1][1], max[i - 1][2]) + arr[i][2]
    }
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("${max[max.size - 1].max()} ${min[min.size - 1].min()}")
    bw.flush()
    bw.close()
}
