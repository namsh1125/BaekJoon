import java.math.BigInteger

/*
* 백준 2407번. 조합
* https://www.acmicpc.net/problem/2407
*/

data class Combination(val n: Int, val r: Int)

const val max = 100

fun main() {

    val comb = initVariable()
    val result = getResult(comb)
    printResult(result)
}

fun initVariable(): Combination {

    val br = System.`in`.bufferedReader()
    var (n, r) = br.readLine().split(' ').map { it.toInt() }

    r = if (r > n / 2) {
        n - r
    } else {
        r
    }

    return Combination(n, r)
}

fun getResult(comb: Combination): BigInteger {

    var dp = Array(size = max + 1) { Array<BigInteger>(size = max + 1) { BigInteger.ONE } }

    for (i in 1..comb.n) {
        for (j in 0..i) {

            if (j == 0 || j == i) {
                dp[i][j] = BigInteger.ONE
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]
            }
        }
    }

    return dp[comb.n][comb.r]
}

fun printResult(result: BigInteger) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
