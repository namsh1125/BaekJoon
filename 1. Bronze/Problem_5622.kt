/*
* 백준 5622번. 다이얼
* https://www.acmicpc.net/problem/5622
*/

fun main() {

    val br = System.`in`.bufferedReader()
    var result = 0

    val line = br.readLine()
    for(element in line) {
        result += when(element) {
            'A', 'B', 'C' -> 3
            'D', 'E', 'F' -> 4
            'G', 'H', 'I' -> 5
            'J', 'K', 'L' -> 6
            'M', 'N', 'O' -> 7
            'P', 'Q', 'R', 'S' -> 8
            'T', 'U', 'V' -> 9
            'W', 'X', 'Y', 'Z' -> 10
            else -> -1
        }
    }

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
