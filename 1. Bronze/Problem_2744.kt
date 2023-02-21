/*
* 백준 2744번. 대소문자 바꾸기
* https://www.acmicpc.net/problem/2744
*/

private fun main() {
    val line = readln()
    val result = getResult(line)
    printResult(result)
}

private fun getResult(line: String): String {
    var result = ""
    for (i in line.indices) {
        when (line[i]) {
            in 'a'..'z' -> result += line[i].uppercaseChar()
            in 'A'..'Z' -> result += line[i].lowercaseChar()
        }
    }

    return result
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
