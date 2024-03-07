/*
* 백준 27866번. 문자와 문자열
* https://www.acmicpc.net/problem/27866
*/

private var s = ""
private var i = -1

private fun main() {
    initVariables()
    val result = getResult(s, i)
    printResult(result)
}

private fun initVariables() {
    val br = System.`in`.bufferedReader()

    s = br.readLine()
    i = br.readLine().toInt()

    br.close()
}

private fun getResult(s: String, i: Int): Char {
    return s[i - 1]
}

private fun printResult(result: Char) {
    val bw = System.out.bufferedWriter()
    bw.write(result.toString())
    bw.flush()
    bw.close()
}
