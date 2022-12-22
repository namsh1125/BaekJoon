import java.util.*

/*
* 백준 2748번. 피보나치 수 2
* https://www.acmicpc.net/problem/2748
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val num = nextInt()
    val dp = Array(size = num + 1) { 0L }
    dp[1] = 1

    for (i in 2..num) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    println(dp[num])

}
