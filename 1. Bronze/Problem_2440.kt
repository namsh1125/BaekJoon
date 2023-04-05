/*
* 백준 2440번. 별 찍기 - 3
* https://www.acmicpc.net/problem/2440
*/

private fun main() {
    val n = readln().toInt()
    for (i in 0 until n) {
        for (j in i until n) {
            print("*")
        }
        println()
    }
}
