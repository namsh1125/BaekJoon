import java.util.*

/*
* 백준 10250번. ACM 호텔
* https://www.acmicpc.net/problem/10250
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val testCase = nextLine().toInt()
    val result = arrayListOf<Int>()

    for (i in 0 until testCase) {

        val (height, _, n) = nextLine().split(' ').map { it.toInt() }

        val room = if (n % height == 0) {
            height * 100 + (n / height)
        } else {
            (n % height) * 100 + (n / height + 1)
        }

        result.add(room)
    }

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
