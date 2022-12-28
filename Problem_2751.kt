import java.util.Scanner

/*
* 백준 2751번. 수 정렬하기 2
* https://www.acmicpc.net/problem/2751
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()
    var num = arrayListOf<Int>()

    for (i in 0 until n) {
        num.add(nextInt())
    }

    num.sort()

    val bw = System.out.bufferedWriter()
    num.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
