/*
* 백준 2754번. 학점계산
* https://www.acmicpc.net/problem/2754
*/

private fun main() {
    val result = when (readln()) {
        "A+" -> 4.3
        "A0" -> 4.0
        "A-" -> 3.7
        "B+" -> 3.3
        "B0" -> 3.0
        "B-" -> 2.7
        "C+" -> 2.3
        "C0" -> 2.0
        "C-" -> 1.7
        "D+" -> 1.3
        "D0" -> 1.0
        "D-" -> 0.7
        else -> 0.0
    }

    printResult(result)
}

private fun printResult(result: Double) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
