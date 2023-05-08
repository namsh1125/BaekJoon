import kotlin.math.max

/*
* 백준 10039번. 평균 점수
* https://www.acmicpc.net/problem/10039
*/

private fun main() {
    var sum = 0

    repeat(5) {
        sum += max(readln().toInt(), 40)
    }

    println(sum / 5)
}
