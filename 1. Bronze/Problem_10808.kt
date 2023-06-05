/*
* 백준 10808번. 알파벳 개수
* https://www.acmicpc.net/problem/10808
*/

private var word = ""

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    word = br.readLine()

    br.close()
}

private fun getResult(): String {
    var count = IntArray(26)

    word.forEach {
        count[it - 'a']++
    }

    var result = ""
    for (i in count.indices) {
        result += "${count[i]} "
    }

    return result
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
