import java.util.*

/*
* 백준 2798번. 블랙잭
* https://www.acmicpc.net/problem/2798
*/


fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, m) = nextLine().split(' ').map { it.toInt() }
    val card = nextLine().split(' ').map { it.toInt() }

    var result = 0

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            for (k in j + 1 until n) {

                val sum = card[i] + card[j] + card[k]

                if (sum > result && sum <= m) {
                    result = sum
                }
            }
        }
    }

    println(result)

}
