import java.util.Scanner

/*
* 백준 3052번. 나머지
* https://www.acmicpc.net/problem/3052
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val num = arrayListOf<Int>()
    for(i in 0 until 10) {
        num.add(nextInt() % 42)
    }

    println(num.distinct().size)
}
