import java.lang.Integer.max

/*
* 백준 14002번. 가장 긴 증가하는 부분 수열 4
* https://www.acmicpc.net/problem/14002
*/

fun main() {

    val sequence = initVariable()
    val result = getResult(sequence)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val sequence = br.readLine().split(' ').map { it.toInt() } as ArrayList

    return sequence
}

fun getResult(sequence: ArrayList<Int>): List<Int> {

    val dp = Array(size = sequence.size) { 1 }
    for (i in 1 until sequence.size) {
        for (j in 0 until i) {
            if (sequence[i] > sequence[j]) {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
    }

    val result = arrayListOf<Int>()
    var len = dp.max()
    for (i in dp.size - 1 downTo 0) {

        if (dp[i] == len) {
            result.add(sequence[i])
            len--
        }
    }

    return result.reversed()
}

fun printResult(result: List<Int>) {

    val bw = System.out.bufferedWriter()
    bw.write("${result.size}\n")
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
