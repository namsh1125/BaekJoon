/*
* 백준 2443번. 별 찍기 - 6
* https://www.acmicpc.net/problem/2443
*/

private fun main() {
    val n = readln().toInt()

    for (i in 0 until n) {
        for (j in 0 until i) print(" ")
        for (j in 0 until 2 * n - (2 * i + 1)) print("*")
        println()
    }
}