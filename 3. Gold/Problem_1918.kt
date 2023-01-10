/*
* 백준 1918번. 후위 표기식
* https://www.acmicpc.net/problem/1918
*/

private fun main() {

    val notation = initVariable()
    val result = getResult(notation)
    printResult(result)
}

private fun initVariable(): String {

    val br = System.`in`.bufferedReader()
    return br.readLine()
}

private fun getResult(notation: String): String {

    var result = ""
    val stack = ArrayDeque<Char>()

    notation.forEach {
        when (it) {

            in 'A'..'Z' -> result += it

            '(' -> stack.add(it)
            ')' -> {
                while(stack.last() != '(') {
                    result += stack.removeLast()
                }
                stack.removeLast()
            }

            '+', '-' -> {
                while(stack.isNotEmpty() && stack.last() != '(') {
                    result += stack.removeLast()
                }
                stack.add(it)
            }

            '*', '/' -> {
                while(stack.isNotEmpty() && (stack.last() == '*' || stack.last() == '/')){
                    result += stack.removeLast()
                }
                stack.add(it)
            }
        }
    }

    while(stack.isNotEmpty()) {
        result += stack.removeLast()
    }

    return result
}

private fun printResult(result: String) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
