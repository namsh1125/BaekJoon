import java.util.*

/*
* 백준 11687번. 팩토리얼 0의 개수
* https://www.acmicpc.net/problem/11687
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val m = nextLong()

    when (val result = getResult(m)) {
        -1L -> println("-1")
        else -> println("$result")
    }

}

fun getResult(m: Long): Long {

    var start = 1L
    var end = 999999999L
    var result = 0L

    while (start <= end) {

        val mid = (start + end) / 2
        val zeroCount = getZeroCount(mid)

        if (zeroCount >= m) {

            if (zeroCount == m) {
                result = mid
            }

            end = mid - 1

        } else {
            start = mid + 1
        }
    }

    return if (result == 0L) {
        -1
    } else {
        result
    }

}

fun getZeroCount(num: Long): Long {

    var key: Long = 5
    var result: Long = 0

    while (key <= num) {
        result += num / key
        key *= 5
    }

    return result
}
