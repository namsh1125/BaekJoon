import java.util.Scanner

/*
* 백준 2920번. 음계
* https://www.acmicpc.net/problem/2920
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val order = nextLine().split(' ').map { it.toInt() }

    if(order == order.sorted()) {
        println("ascending")
    } else if(order == order.sortedDescending()) {
        println("descending")
    } else {
        println("mixed")
    }
}
