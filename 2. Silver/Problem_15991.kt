/*
* 백준 15991번. 1, 2, 3 더하기 6
* https://www.acmicpc.net/problem/15991
*/

private var dp = Array(size = 100001) { 0L }
private const val MOD = 1000000009

fun main() {

    val testCase = initVariable()
    fillDp()
    val result = getResult(testCase)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val testCase = arrayListOf<Int>()
    for (i in 0 until n) {
        testCase.add(br.readLine().toInt())
    }

    return testCase
}

fun fillDp() {

    dp[1] = 1
    dp[2] = 2
    dp[3] = 2
    dp[4] = 3
    dp[5] = 3
    dp[6] = 6

    for (i in 7 until dp.size) {
        dp[i] = (dp[i - 2] + dp[i - 4] + dp[i - 6]) % MOD
    }
}

fun getResult(testCase: ArrayList<Int>): String {

    var result = ""
    for(i in testCase.indices) {
        result += "${dp[testCase[i]]}\n"
    }

    return result
}

fun printResult(result: String) {

    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}
