import java.util.StringTokenizer
import kotlin.math.max

/*
* 백준 1932번. 정수 삼각형
* https://www.acmicpc.net/problem/1932
*/

private lateinit var triangle: Array<IntArray>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    triangle = Array(n) { IntArray(n) { -1 } }
    for (i in triangle.indices) {
        val st = StringTokenizer(br.readLine())
        for (j in 0..i) {
            triangle[i][j] = st.nextToken().toInt()
        }
    }

    br.close()
}

private fun getResult(): Int {
    val dp = Array(triangle.size) { IntArray(triangle.size) { 0 } }
    dp[0][0] = triangle[0][0]

    for (i in 0 until triangle.size - 1) {
        for (j in 0..i) {
            dp[i + 1][j] = max(dp[i + 1][j], dp[i][j] + triangle[i + 1][j])
            dp[i + 1][j + 1] = max(dp[i + 1][j + 1], dp[i][j] + triangle[i + 1][j + 1])
        }
    }

    return dp[dp.size - 1].max()
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
