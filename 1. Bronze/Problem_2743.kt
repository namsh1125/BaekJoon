/*
* 백준 2743번. 단어 길이 재기
* https://www.acmicpc.net/problem/2743
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val str = br.readLine()
    printResult(str.length)
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
