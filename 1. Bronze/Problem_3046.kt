/*
* 백준 3046번. R2
* https://www.acmicpc.net/problem/3046
*/

private fun main() {
    val (r1, s) = readln().split(' ').map { it.toInt() }
    println(s * 2 - r1)
}