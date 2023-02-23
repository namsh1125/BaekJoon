/*
* 백준 9086번. 문자열
* https://www.acmicpc.net/problem/9086
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = arrayListOf<String>()

    val tc = br.readLine().toInt()
    repeat(tc) {
        val line = br.readLine()
        result.add("${line[0]}${line.last()}")
    }
    br.close()

    printResult(result)
}

private fun printResult(result: ArrayList<String>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
