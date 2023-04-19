/*
* 백준 4470번. 줄번호
* https://www.acmicpc.net/problem/4470
*/

private fun main() {
    val n = readln().toInt()
    repeat(n) { i ->
        val line = readln()
        println("${i + 1}. $line")
    }
}
