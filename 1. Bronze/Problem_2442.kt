/*
* 백준 2442번. 별 찍기 - 5
* https://www.acmicpc.net/problem/2442
*/

private fun main() {
    val n = readln().toInt()

    for (i in 0 until n) {
        for (j in 0 until n - i - 1) print(" ")
        for (j in 0 until 2 * i + 1) print("*")
        println()
    }
}