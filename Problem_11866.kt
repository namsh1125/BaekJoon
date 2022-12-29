import java.util.*

/*
* 백준 11866번. 요세푸스 문제 0
* https://www.acmicpc.net/problem/11866
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, k) = nextLine().split(' ').map { it.toInt() }
    val num = LinkedList<Int>()
    var result = "<"

    for(i in 1 .. n) {
        num.add(i)
    }

    while(num.isNotEmpty()) {

        for(i in 0 until k - 1) {
            num.add(num.removeFirst())
        }

        result += if(num.size != 1) {
            "${num.removeFirst()}, "
        } else {
            "${num.removeFirst()}>"
        }
    }

    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}
