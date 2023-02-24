/*
* 백준 13172번. Σ
* https://www.acmicpc.net/problem/13172
*/

data class Dice(val n: Long, val sum: Long)
private val diceList = ArrayList<Dice>()

private const val MOD = 1_000_000_007L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val m = br.readLine().toInt()

    repeat(m) {
        val (n, s) = br.readLine().split(' ').map { it.toLong() }
        diceList.add(Dice(n, s))
    }

    br.close()
}

private fun getResult(): Long {
    var result = 0L

    diceList.forEach { dice ->
        result += (dice.sum * power(dice.n, MOD - 2)) % MOD
        result %= MOD
    }

    return result
}

private fun power(n: Long, m: Long): Long {
    return if (m == 1L) n
    else if (m and 1L == 1L) n * power(n, m - 1) % MOD
    else {
        val result = power(n, m / 2)
        result * result % MOD
    }
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
