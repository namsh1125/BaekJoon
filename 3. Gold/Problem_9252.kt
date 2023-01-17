import java.lang.Integer.max

/*
* 백준 9252번. LCS 2
* https://www.acmicpc.net/problem/9252
*/

private var str1 = ""
private var str2 = ""

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    str1 = br.readLine()
    str2 = br.readLine()
}

private fun getResult(): String {

    val dp = getDp()
    var result = ""

    var i = str1.length
    var j = str2.length

    while (true) {
        if (dp[i][j] == 0) break

        if (str1[i - 1] == str2[j - 1]) {
            result = str1[i - 1] + result
            i--
            j--
        } else {
            if (dp[i - 1][j] > dp[i][j - 1]) i--
            else j--
        }
    }

    return result
}

private fun getDp(): Array<IntArray> {

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

    return dp
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.length}\n")
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
