import kotlin.math.min

/*
* 백준 17404번. RGB거리 2
* https://www.acmicpc.net/problem/17404
*/

data class Cost(val red: Int, val green: Int, val blue: Int)

private val costList = arrayListOf<Cost>()
private const val INF = 1000 * 1000 + 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    repeat(n) {
        val (r, g, b) = br.readLine().split(' ').map { it.toInt() }
        costList.add(Cost(r, g, b))
    }

    br.close()
}

private fun getResult(): Int {
    val red = getMinCost(0) // 1번 집이 빨간색 집인 경우
    val green = getMinCost(1) // 1번 집이 초록색 집인 경우
    val blue = getMinCost(2) // 1번 집이 파란색 집인 경우

    return min(red, min(green, blue))
}

private fun getMinCost(start: Int): Int {
    val n = costList.size
    val dp = Array(size = n) { IntArray(size = 3) { INF } }

    // 1번집에 색칠
    when (start) {
        0 -> dp[0][0] = costList[0].red
        1 -> dp[0][1] = costList[0].green
        2 -> dp[0][2] = costList[0].blue
    }

    for (i in 1 until n) {
        dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + costList[i].red
        dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + costList[i].green
        dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + costList[i].blue
    }

    // 1번집에 색칠한 색깔은 사용 불가
    when (start) {
        0 -> dp[n - 1][0] = Int.MAX_VALUE
        1 -> dp[n - 1][1] = Int.MAX_VALUE
        2 -> dp[n - 1][2] = Int.MAX_VALUE
    }

    var result = Int.MAX_VALUE
    dp[n - 1].forEach {
        result = min(result, it)
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
