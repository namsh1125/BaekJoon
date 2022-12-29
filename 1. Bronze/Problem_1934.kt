import java.util.Scanner

/*
* 백준 1934번. 최소공배수
* https://www.acmicpc.net/problem/1934
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val t = nextLine().toInt()
    val resultList = arrayListOf<Int>()

    for (i in 0 until t) {
        val (a, b) = nextLine().split(' ').map { it.toInt() }
        resultList.add(lcm(a, b))
    }

    for (i in resultList.indices) {
        println(resultList[i])
    }

}

fun lcm(a: Int, b: Int): Int {
    return a * b / gcd(a, b)
}

fun gcd(a: Int, b: Int): Int {

    return if (b == 0) {
        a
    } else {
        gcd(b, a % b)
    }
}
