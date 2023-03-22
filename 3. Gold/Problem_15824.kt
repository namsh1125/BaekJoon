/*
* 백준 15824번. 너 봄에는 캡사이신이 맛있단다
* https://www.acmicpc.net/problem/15824
*/

private var n = -1
private lateinit var arr: List<Long>

private const val MOD = 1_000_000_007

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    n = br.readLine().toInt()
    arr = br.readLine().split(' ').map { it.toLong() }

    br.close()
}

private fun getResult(): Long {
    arr = arr.sorted()

    var result = 0L
    for (i in arr.indices) {
        result += arr[i] * (pow(2, i) - pow(2, n - i - 1))
        result %= MOD
    }

    return result
}

private fun pow(n: Int, time: Int): Long {
    return if (time == 0) 1L
    else if (time and 1 == 1) n * pow(n, time - 1) % MOD
    else {
        val half = pow(n, time / 2)
        half * half % MOD
    }
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
