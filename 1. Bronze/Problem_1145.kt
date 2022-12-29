import java.util.*

/*
* 백준 1145번. 적어도 대부분의 배수
* https://www.acmicpc.net/problem/1145
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val num = nextLine().split(' ').map { it.toInt() }
    var result = 0
    var i = 1

    do {
        var count = 0

        for (j in num.indices) {
            if (i % num[j] == 0) count++
            if (count == 3) result = i
        }

        i++

    } while (count < 3)

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.close()
}
