import java.util.*

/*
* 백준 10430번. 나머지
* https://www.acmicpc.net/problem/10430
*/


fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val a = nextInt()
    val b = nextInt()
    val c = nextInt()

    println((a + b) % c)
    println(((a % c) + (b % c)) % c)
    println((a * b) % c)
    println(((a % c) * (b % c)) % c)
}
