/*
* 백준 11726번. 2×n 타일링
* https://www.acmicpc.net/problem/11726
*/

const val max = 1000

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

    val dp = IntArray(size = max + 1) { 0 }
    dp[1] = 1
    dp[2] = 2

    for (i in 3..n) {
        dp[i] = (dp[i - 2] + dp[i - 1]) % 10007
    }

    return dp[n]
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
