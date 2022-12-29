import java.util.*

/*
* 백준 14912번. 숫자 빈도수
* https://www.acmicpc.net/problem/14912
*/

fun main() = with(Scanner(System.`in`)) {

    val n = nextInt()
    val digit = nextInt()
    var freq = 0

    for (i in 1..n) {

        var num = i

        while (num / 10 != 0) {

            if (num % 10 == digit) {
                freq++
            }

            num = num /10
        }

        if(num == digit) {
            freq++
        }

    }

    println(freq)

}
