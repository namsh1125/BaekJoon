import java.util.*

/*
* 백준 11057번. 오르막 수
* https://www.acmicpc.net/problem/11057
*/


fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val length = nextInt()
    val dp = Array(size = length + 1) { IntArray(size = 10) { 0 } } // 배열의 row는 길이, column은 0 .. 9

    // 자리수 1
    for (i in 0 until 10) {
        dp[1][i] = 1
    }

    // 자리수 2 이상
    for (i in 2..length) {
        for (j in 0 until 10) {
            for (k in 0..j) {
                dp[i][j] += dp[i - 1][k]
            }
            dp[i][j] %= 10007
        }
    }

    var result: Long = 0
    for (i in 0 until 10) {
        result += dp[length][i]
    }

    println(result % 10007)
}
