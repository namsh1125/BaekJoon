/*
* 백준 2444번. 별 찍기 - 7
* https://www.acmicpc.net/problem/2444
*/

private fun main() {
    val n = readln().toInt()

    for (i in 0 until n) {
        for (j in n - i - 2 downTo 0) print(" ")
        for (j in 0 until 2 * i + 1) print("*")
        println()
    }

    for (i in n - 1 downTo 1) {
        for (j in n - i - 1 downTo 0) print(" ")
        for (j in 0 until 2 * i - 1) print("*")
        println()
    }
}
