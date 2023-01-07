import java.lang.Integer.max

/*
* 백준 9251번. LCS
* https://www.acmicpc.net/problem/9251
*/

private var str1 = ""
private var str2 = ""

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    str1 = br.readLine()
    str2 = br.readLine()
}

fun getResult(): Int {

    val dp = Array(size = str1.length + 1) { IntArray(size = str2.length + 1) { 0 } }

    for (i in 1..str1.length) {
        for (j in 1..str2.length) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j])
            }
        }
    }

    return dp[str1.length][str2.length]
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
