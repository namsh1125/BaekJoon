/*
* 백준 5717번. 상근이의 친구들
* https://www.acmicpc.net/problem/5717
*/

private fun main() {
    while (true) {
        val (a, b) = readln().split(' ').map { it.toInt() }
        if (a == 0 && b == 0) break
        else println(a + b)
    }
}
