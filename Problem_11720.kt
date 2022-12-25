import java.util.Scanner

/*
* 백준 11720번. 숫자의 합
* https://www.acmicpc.net/problem/11720
*/

fun main() = with(Scanner(System.`in`.bufferedReader())){

    nextLine()
    val num = nextLine()
    var result = 0

    for(i in num.indices) {
        result += num[i] - '0'
    }

    println(result)

}
