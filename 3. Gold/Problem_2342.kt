import kotlin.math.abs
import kotlin.math.min

/*
* 백준 2342번. Dance Dance Revolution
* https://www.acmicpc.net/problem/2342
*/

private lateinit var list: ArrayList<Int>
private const val INF = 4 * 100_000 + 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    list = br.readLine().split(' ').map { it.toInt() } as ArrayList
    list.removeAt(list.size - 1) // 마지막 0 제거
    br.close()
}

private fun getResult(): Int {
    val dp = dynamicProgramming()
    return getResult(dp)
}

private fun dynamicProgramming(): Array<Array<IntArray>> {

    val dp = Array(size = list.size + 1) { Array(size = 5) { IntArray(size = 5) { INF } } } // n번째, 왼쪽발, 오른쪽발
    dp[0][0][0] = 0 // 처음 위치

    for (i in list.indices) {

        // 왼발만 이동
        for (right in 0 until 5) {
            for (left in 0 until 5) {
                dp[i + 1][list[i]][right] = min(dp[i + 1][list[i]][right], dp[i][left][right] + getPower(left, list[i]))
            }
        }

        // 오른발만 이동
        for (left in 0 until 5) {
            for (right in 0 until 5) {
                dp[i + 1][left][list[i]] = min(dp[i + 1][left][list[i]], dp[i][left][right] + getPower(right, list[i]))
            }
        }
    }

    return dp
}

private fun getPower(from: Int, to: Int): Int {
    return if (from == 0) {
        if (to == 0) 0
        else 2
    } else if (from == to) 1
    else if (abs(from - to) == 2) 4
    else 3
}

private fun getResult(dp: Array<Array<IntArray>>): Int {

    var result = Int.MAX_VALUE
    for (left in 0 until 5) {
        for (right in 0 until 5) {
            result = min(result, dp[list.size][left][right])
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
