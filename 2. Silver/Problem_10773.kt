import java.util.*

/*
* 백준 10773번. 제로
* https://www.acmicpc.net/problem/10773
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    var k = nextInt()
    val money = LinkedList<Int>()

    while(k --> 0) {

        when(val num = nextInt()) {
            0 -> money.removeLast()
            else -> money.add(num)
        }
    }

    var result = 0
    money.forEach {
        result += it
    }

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.close()
}
