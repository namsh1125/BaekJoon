import java.util.StringTokenizer

/*
* 백준 1541번. 잃어버린 괄호
* https://www.acmicpc.net/problem/1541
*/

private fun main() {
    val line = readln()
    val result = getResult(line)
    printResult(result)
}

private fun getResult(line: String): Int {

    var result = Int.MAX_VALUE
    val subtraction = StringTokenizer(line, "-")

    while (subtraction.hasMoreTokens()) {

        var temp = 0
        val addition = StringTokenizer(subtraction.nextToken(), "+")

        while (addition.hasMoreTokens()) {
            temp += addition.nextToken().toInt()
        }

        if (result == Int.MAX_VALUE) {
            result = temp
        } else {
            result -= temp
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
