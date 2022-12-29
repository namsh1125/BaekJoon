import java.util.Scanner

/*
* 백준 1758번. 알바생 강호
* https://www.acmicpc.net/problem/1758
*/


fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()
    val tip = Array(size = n) { 0 }

    for (i in 0 until n) {
        tip[i] = nextInt()
    }

    tip.sortDescending()

    var result = 0L

    for(i in tip.indices) {

        val tip = tip[i] - i

        if(tip > 0) {
            result += tip
        }
    }

    println(result)

}
