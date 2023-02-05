/*
* 백준 9935번. 문자열 폭발
* https://www.acmicpc.net/problem/9935
*/

private lateinit var string: String
private var find = ""

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    string = br.readLine()
    find = br.readLine()

    br.close()
}

private fun getResult(): String {
    val stack = ArrayDeque<Char>()

    for (i in string.indices) {
        stack.add(string[i])

        if (stack.size >= find.length) {
            var contain = true
            for (j in find.indices) {
                if (stack[stack.size - find.length + j] != find[j]) {
                    contain = false
                    break
                }
            }

            if (contain) {
                repeat(find.length) { stack.removeLast() }
            }
        }
    }

    var sb = StringBuilder()
    for (c in stack) {
        sb.append(c)
    }

    return if (sb.isNotEmpty()) sb.toString() else "FRULA"
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
