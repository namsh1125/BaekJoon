/*
* 백준 2522번. 별 찍기 - 12
* https://www.acmicpc.net/problem/2522
*/

private fun main() {
    val n = readln().toInt()

    for (i in 1..n) {
        for (j in 1..n - i) print(" ")
        for (j in 1..i) print("*")
        println()
    }

    for (i in n - 1 downTo 1) {
        for (j in 1..n - i) print(" ")
        for (j in 1..i) print("*")
        println()
    }
}
