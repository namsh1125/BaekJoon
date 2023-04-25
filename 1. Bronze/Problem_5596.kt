import kotlin.math.max

/*
* 백준 5596번. 시험 점수
* https://www.acmicpc.net/problem/5596
*/

private fun main() {
    var result = 0

    repeat(2) {
        val (a, b, c, d) = readln().split(' ').map { it.toInt() }
        result = max(result, a + b + c + d)
    }

    println(result)
}
