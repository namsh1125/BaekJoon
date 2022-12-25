import java.util.Scanner

/*
* 백준 10951번. A+B - 4
* https://www.acmicpc.net/problem/10951
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val result = arrayListOf<Int>()

    while (hasNextInt()) {

        val a = nextInt()
        val b = nextInt()
        result.add(a + b)
    }

    for (i in result.indices) {
        println(result[i])
    }

}
