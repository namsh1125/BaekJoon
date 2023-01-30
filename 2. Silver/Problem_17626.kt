import kotlin.math.min

/*
* 백준 17626번. Four Squares
* https://www.acmicpc.net/problem/17626
*/

private fun main() {
    val n = initVariable()
    val result = getResult(n)
    printResult(result)
}

private fun initVariable(): Int {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    br.close()
    return n
}

private fun getResult(n: Int): Int {

    val dp = IntArray(size = n + 1) { 0 }
    dp[1] = 1

    for (i in 2 until dp.size) {
        dp[i] = dp[i - 1] + 1

        var j = 2
        while (j * j <= i) {
            dp[i] = min(dp[i], dp[i - j * j] + 1)
            j++
        }
    }

    return dp[n]
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
