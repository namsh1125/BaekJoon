import java.util.Scanner

/*
* 백준 2739번. 구구단
* https://www.acmicpc.net/problem/2739
*/

fun main() = with(Scanner(System.`in`.bufferedReader())){

    val n = nextInt()

    for(i in 1 .. 9) {
        println("$n * $i = ${n * i}")
    }
}
