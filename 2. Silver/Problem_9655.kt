/*
* 백준 9655번. 돌 게임
* https://www.acmicpc.net/problem/9655
*/

private var n = -1

private fun main() {
    n = readln().toInt()
    val result = getResult()
    printResult(result)
}

private fun getResult(): String {
    return if (n % 2 == 1) "SK" else "CY"
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
