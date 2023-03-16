/*
* 백준 11021번. A+B - 7
* https://www.acmicpc.net/problem/11021
*/

private fun main() {
    val tc = readln().toInt()
    repeat(tc) { i ->
        val (a, b) = readln().split(' ').map { it.toInt() }
        println("Case #${i + 1}: ${a + b}")
    }
}
