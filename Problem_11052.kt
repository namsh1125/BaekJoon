import java.lang.Integer.max
import java.util.*

/*
* 백준 11052번. 카드 구매하기
* https://www.acmicpc.net/problem/11052
*/


fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLine().toInt()
    val p = nextLine().split(' ').map { it.toInt() }.toMutableList()

    val dp = MutableList(size = n + 1) { 0 }
    p.add(index = 0, element = 0)

    for (i in 0..n) {
        for (j in 0..i) {
            dp[i] = max(dp[i], dp[i - j] + p[j])
        }
    }

    println(dp[n])

}
