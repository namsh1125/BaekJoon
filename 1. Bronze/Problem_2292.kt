/*
* 백준 2292번. 벌집
* https://www.acmicpc.net/problem/2292
*/

fun main() {

    val n = initVariable()
    val result = getResult(n)
    printResult(result)
}

fun initVariable(): Int {

    val br = System.`in`.bufferedReader()
    return br.readLine().toInt()
}

fun getResult(n: Int): Int {

    var end = 1
    var result = 1
    while (n > end) {
        end += result * 6
        result++
    }

    return result
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
