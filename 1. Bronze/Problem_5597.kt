/*
* 백준 5597번. 과제 안 내신 분..?
* https://www.acmicpc.net/problem/5597
*/

private val submit = BooleanArray(31) { false }

private fun main() {
    val br = System.`in`.bufferedReader()

    repeat(28) {
        val num = br.readLine().toInt()
        submit[num] = true
    }

    printResult()
}

private fun printResult() {
    val bw = System.out.bufferedWriter()

    for (i in 1..30) {
        if (!submit[i]) bw.write("$i\n")
    }

    bw.flush()
    bw.close()
}
