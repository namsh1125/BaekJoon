import java.util.Scanner

/*
* 백준 1309번. 동물원
* https://www.acmicpc.net/problem/1309
*/

const val mod = 9901

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()
    val dp = Array(size = n) { Array(size = 3) { 0 } } // n번째 줄에 사자 위치. 0 -> 없음, 1 -> 왼쪽, 2 -> 오른쪽
    dp[0][0] = 1
    dp[0][1] = 1
    dp[0][2] = 1

    for (i in 1 until n) {
        dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % mod
        dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod
        dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod
    }

    val result = (dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2]) % mod

    println(result)

}
