import java.util.Scanner

/*
* 백준 1032번. 명령 프롬프트
* https://www.acmicpc.net/problem/1032
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLine().toInt()
    val result = nextLine().toCharArray()

    for (i in 1 until n) {

        val sentence = nextLine()

        for (j in sentence.indices) {

            if (result[j] == '?')
                continue
            else if (result[j] != sentence[j]) {
                result[j] = '?'
            }
        }

    }

    println(result)

}
