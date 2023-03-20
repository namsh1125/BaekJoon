/*
* 백준 11022번. A+B - 8
* https://www.acmicpc.net/problem/11022
*/

private fun main() {
    val n = readln().toInt()
    repeat(n) { i ->
        val (a, b) = readln().split(' ').map { it.toInt() }
        println("Case #${i + 1}: $a + $b = ${a + b}")
    }
}
