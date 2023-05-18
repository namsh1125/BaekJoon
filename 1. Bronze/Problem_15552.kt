/*
* 백준 15552번. 빠른 A+B
* https://www.acmicpc.net/problem/15552
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val result = StringBuilder()

    repeat(n) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        result.append("${a + b}\n")
    }

    print(result)
}
