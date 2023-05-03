/*
* 백준 17255번. N으로 만들기
* https://www.acmicpc.net/problem/17255
*/

private var n = ""

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    n = br.readLine()

    br.close()
}

private fun getResult(): Int {
    val result = mutableSetOf<String>()

    fun dfs(made: String, start: Int, end: Int, procedure: String) {
        if (made == n) {
            result.add(procedure)
            return
        }

        var str: String
        if (start > 0) {
            str = n[start - 1] + made
            dfs(str, start - 1, end, procedure + str)
        }

        if (end < n.length - 1) {
            str = made + n[end + 1]
            dfs(str, start, end + 1, procedure + str)
        }
    }

    for (i in n.indices) {
        dfs("${n[i]}", i, i, "${n[i]}")
    }

    return result.size
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}