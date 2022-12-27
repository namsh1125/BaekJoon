import java.util.*

/*
* 백준 11050번. 이항 계수 1
* https://www.acmicpc.net/problem/11050
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, k) = nextLine().split(' ').map { it.toInt() }
    println("${nCr(n, k)}")
}

fun nCr(n: Int, r: Int): Int {

    var result = 1
    for (i in 1..r) {
        result = result * (n - i + 1) / i
    }

    return result
}
