import java.util.Scanner

/*
* 백준 11654번. 아스키 코드
* https://www.acmicpc.net/problem/11654
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val text = nextLine()

    if (text.length > 2)
        return

    println(text[0].code)

}
