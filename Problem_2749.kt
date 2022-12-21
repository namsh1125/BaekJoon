import java.util.*

/*
* 백준 2749번. 피보나치 수
* https://www.acmicpc.net/problem/2749
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLong()
    val dp = Array(size = 1500000) { 0L }
    dp[1] = 1

    for (i in 2 until 1500000) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000
    }

    println(dp[(n % 1500000).toInt()])

}
