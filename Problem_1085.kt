import java.util.*

/*
* 백준 1085번. 직사각형에서 탈출
* https://www.acmicpc.net/problem/1085
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (x, y, w, h) = nextLine().split(' ').map { it.toInt() }

    val length = arrayListOf(x, y, w - x, h - y)
    length.sort()

    val result = length[0]

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.close()
}
