import java.util.Scanner

/*
* 백준 2609번. 최대공약수와 최소공배수
* https://www.acmicpc.net/problem/2609
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (num1, num2) = nextLine().split(' ').map { it.toInt() }
    val gcd = gcd(num1, num2)
    val lcm = lcm(num1, num2, gcd)

    println("$gcd")
    println("$lcm")
}

fun lcm(num1: Int, num2: Int, gcd: Int): Int {
    return num1 * num2 / gcd
}

fun gcd(num1: Int, num2: Int): Int {
    return if (num2 == 0) num1 else gcd(num2, num1 % num2)
}
