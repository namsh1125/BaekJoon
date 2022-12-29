import java.util.Scanner

/*
* 백준 2475번. 검증수
* https://www.acmicpc.net/problem/2475
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val num = nextLine().split(' ').map { it.toInt() }
    var result = 0

    for (i in num.indices) {
        result += num[i] * num[i]
    }

    println(result % 10)
}
