/*
* 백준 13977번. 이항 계수와 쿼리
* https://www.acmicpc.net/problem/13977
*/

private const val MOD = 1_000_000_007
private val fact = LongArray(4_000_001) { 1L }

private fun main() {
    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()

    initFactorial()

    val sb = StringBuilder()
    repeat(tc) {
        val (n, k) = br.readLine().split(' ').map { it.toInt() }
        sb.append("${getResult(n, k)}\n")
    }
    br.close()

    printResult(sb.toString())
}

private fun initFactorial() {
    for (i in 2 until fact.size) {
        fact[i] = fact[i - 1] * i % MOD
    }
}

private fun getResult(n: Int, k: Int): Long {
    var a = fact[n] // N!
    var b = fact[k] * fact[n - k] % MOD // K! * (N - K)!
    b = mul(b, MOD - 2) // b^(MOD - 2)

    return a * b % MOD
}

private fun mul(n: Long, time: Int): Long {
    return if (time == 0) 1L
    else if (time % 2 == 1) n * mul(n, time - 1) % MOD
    else {
        val half = mul(n, time / 2)
        half * half % MOD
    }
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
