/*
* 백준 10807번. 개수 세기
* https://www.acmicpc.net/problem/10807
*/

fun main() {
    val n = readln().toInt()
    val arr = readln().split(' ').map { it.toInt() }
    val find = readln().toInt()

    var result = 0
    arr.forEach {
        if (it == find) result++
    }

    printResult(result)
}

fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
