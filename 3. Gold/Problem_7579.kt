import java.util.*
import kotlin.math.max
import kotlin.math.min

/*
* 백준 7579번. 앱
* https://www.acmicpc.net/problem/7579
*/

private var count = -1
private lateinit var memoryList: List<Int>
private lateinit var costList: List<Int>

private var needMemory = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val st = StringTokenizer(br.readLine())
    count = st.nextToken().toInt()
    needMemory = st.nextToken().toInt()

    memoryList = br.readLine().split(' ').map { it.toInt() }
    costList = br.readLine().split(' ').map { it.toInt() }

    br.close()
}

private fun getResult(): Int {

    var cost = 0
    costList.forEach { cost += it }

    var result = Int.MAX_VALUE
    val dp = Array(count + 1) { IntArray(cost + 1) { 0 } } // i번째 앱까지 탐색했을 때 j 비용을 소모해서 얻을 수 있는 최대 메모리
    for (i in 1..count) {
        for (j in 0..cost) {
            if (j - costList[i - 1] >= 0) {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - costList[i - 1]] + memoryList[i - 1])
            } else {
                dp[i][j] = dp[i - 1][j]
            }

            if (dp[i][j] >= needMemory) result = min(result, j)
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
