import java.lang.Integer.max

/*
* 백준 11055번. 가장 큰 증가 부분 수열
* https://www.acmicpc.net/problem/11055
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

fun getResult(sequence: ArrayList<Int>): Int {

    val dp = Array(size = sequence.size) { sequence[it] }
    for (i in 1 until sequence.size) {
        for (j in 0 until i) {
            if (sequence[i] > sequence[j]) {
                dp[i] = max(dp[i], dp[j] + sequence[i])
            }
        }
    }

    return dp.max()
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
