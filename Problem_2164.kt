import java.util.*

/*
* 백준 2164번. 카드2
* https://www.acmicpc.net/problem/2164
*/

fun main() = with(Scanner(System.`in`.bufferedReader())){

    val n = nextInt()
    val card = LinkedList<Int>()

    for(i in 1 .. n) {
        card.add(i)
    }

    while(card.size != 1) {
        card.poll()
        card.add(card.poll())
    }

    println(card[0])
}
