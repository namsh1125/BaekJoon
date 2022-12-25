import java.util.Scanner

/*
* 백준 2908번. 상수
* https://www.acmicpc.net/problem/2908
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (a, b) = nextLine().split(' ')
    val na = a.chunked(1).map { it.toInt() }.reversed()
    val nb = b.chunked(1).map { it.toInt() }.reversed()

    val nna = 100 * na[0] + 10 * na[1] + na[2]
    val nnb = 100 * nb[0] + 10 * nb[1] + nb[2]

    if(nna > nnb) {
        println(nna)
    } else {
        println(nnb)
    }

}
