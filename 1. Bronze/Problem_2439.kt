import java.util.Scanner

/*
* 백준 2439번. 별 찍기 - 2
* https://www.acmicpc.net/problem/2439
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val num = nextInt()

    for (i in 1 .. num) {

        for (j in 0 until num - i) {
            print(" ")
        }

        for (j in num - i until num) {
            print("*")
        }

        println()
    }

}
