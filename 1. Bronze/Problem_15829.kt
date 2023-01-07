/*
* 백준 15829번. Hashing
* https://www.acmicpc.net/problem/15829
*/

const val r = 31
const val m = 1_234_567_891

fun main() {

    val str = initVariable()
    val result = getResult(str)
    printResult(result)
}

fun initVariable(): String {

    val br = System.`in`.bufferedReader()
    br.readLine()

    return br.readLine()
}

fun getResult(str: String): Long {

    var sum = 0L
    for (i in str.indices) {

        val ai = str[i] - 'a' + 1
        var ri = 1L
        for (j in 0 until i) {
            ri = ri * r % m
        }

        sum += ai * ri % m
    }

    return sum % m
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
