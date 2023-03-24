/*
* 백준 11401번. 이항 계수 3
* https://www.acmicpc.net/problem/11401
*/

private const val MOD = 1_000_000_007L

private var n = -1L
private var k = -1L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toLong() }.also {
        n = it[0]
        k = it[1]
    }
    br.close()
}

private fun getResult(): Long {
    // N!
    var a = 1L
    for (i in 1..n) {
        a = a * i % MOD
    }

    // K! * (N-K)!
    var b = 1L
    for (i in 1..k) {
        b = b * i % MOD
    }

    for (i in 1..n - k) {
        b = b * i % MOD
    }

    // b^(MOD -2)
    b = mul(b, MOD - 2)

    return a * b % MOD
}

private fun mul(n: Long, time: Long): Long {
    return if (time == 0L) 1
    else if (time % 2 == 1L) n * mul(n, time - 1) % MOD
    else {
        val half = mul(n, time / 2)
        half * half % MOD
    }
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
