/*
* 백준 2441번. 별 찍기 - 4
* https://www.acmicpc.net/problem/2441
*/

private fun main() {
    val n = readln().toInt()

    repeat(n) {
        for (i in 0 until it) print(" ")
        for (i in it until n) print("*")
        println()
    }
}
