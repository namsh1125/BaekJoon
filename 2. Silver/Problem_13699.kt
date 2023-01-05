/*
* 백준 13699번. 점화식
* https://www.acmicpc.net/problem/13699
*/

fun main() {

    val num = initVariable()
    val result = getResult(num)
    printResult(result)
}

fun initVariable(): Int {
    val br = System.`in`.bufferedReader()
    return br.readLine().toInt()
}

fun getResult(num: Int): Long {

    val t = Array(size = 36) { 0L }
    t[0] = 1

    for (i in 1..35) {
        for (j in 0 until i) {
            t[i] += t[j] * t[i - j - 1]
        }
    }

    return t[num]
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
