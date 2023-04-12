/*
* 백준 1500번. 최대 곱
* https://www.acmicpc.net/problem/1500
*/

private var s = -1
private lateinit var arr: List<Int>

private fun main() {
    val (s, k) = readln().split(' ').map { it.toInt() }
    val result = getResult(s, k)
    printResult(result)
}

private fun getResult(s: Int, k: Int): Long {
    val arr = IntArray(k) { s / k }
    for (i in 0 until s % k) {
        arr[i]++
    }

    var result = 1L
    for (i in 0 until k) {
        result *= arr[i]
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
