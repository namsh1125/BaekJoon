import java.util.Scanner

/*
* 백준 1110번. 더하기 사이클
* https://www.acmicpc.net/problem/1110
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val k = nextInt()

    println(getResult(num = k))

}

fun getResult(num: Int): Int {

    var result = 0
    var newNum = num

    do {

        newNum = if (newNum / 10 == 0) {
            newNum * 10 + newNum
        } else {
            newNum % 10 * 10 + (newNum / 10 + newNum % 10) % 10
        }

        result++

    } while (newNum != num)

    return result
}
