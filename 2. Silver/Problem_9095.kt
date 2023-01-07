/*
* 백준 9095번. 1, 2, 3 더하기
* https://www.acmicpc.net/problem/9095
*/

const val max = 10

fun main() {

    val testcaseList = initVariable()
    val result = getResult(testcaseList)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val testcaseList = arrayListOf<Int>()
    repeat(n) {
        testcaseList.add(br.readLine().toInt())
    }

    return testcaseList
}

fun getResult(testcaseList: ArrayList<Int>): ArrayList<Int> {

    val resultList = arrayListOf<Int>()
    val dp = Array(size = max + 1) { 0 }
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for (i in 4 until dp.size) {
        dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1]
    }

    testcaseList.forEach {
        resultList.add(dp[it])
    }

    return resultList
}

fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
