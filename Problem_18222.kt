import java.util.Scanner

/*
* 백준 18222번. 투에-모스 문자열
* https://www.acmicpc.net/problem/18222
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val k = nextLong()
    println(getResult(k - 1))
}

fun getResult(num: Long): Int {

    // 투에 - 모스 수열 점화식
    // t[0]= 0,
    // t[2n] = t[n],
    // t[2n+1] = 1 − t[n]

    return if (num == 0L) {
        0
    } else if (num == 1L) {
        1
    } else if (num % 2 == 0L) {
        getResult(num / 2)
    } else {
        1 - getResult(num / 2)
    }
}
