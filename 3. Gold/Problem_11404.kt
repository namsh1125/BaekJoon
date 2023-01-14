import kotlin.math.min

/*
* 백준 11404번. 플로이드
* https://www.acmicpc.net/problem/11404
*/

private const val INF = 999_999_999

private fun main() {
    val cost = initVariable()
    val result = floydWarshall(cost)
    printResult(result)
}

private fun initVariable(): Array<IntArray> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val cost = Array(size = n + 1) { IntArray(size = n + 1) { INF } }

    for (i in cost.indices) {
        cost[i][i] = 0
    }

    repeat(m) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        cost[a][b] = min(cost[a][b], c)
    }

    return cost
}

private fun floydWarshall(cost: Array<IntArray>): Array<IntArray> {

    for (k in 1 until cost.size) {
        for (i in 1 until cost.size) {
            for (j in 1 until cost.size) {
                cost[i][j] = min(cost[i][j], cost[i][k] + cost[k][j])
            }
        }
    }

    return cost
}

private fun printResult(result: Array<IntArray>) {

    val bw = System.out.bufferedWriter()
    for (i in 1 until result.size) {
        for (j in 1 until result[i].size) {
            if (result[i][j] != INF) {
                bw.write("${result[i][j]} ")
            } else {
                bw.write("0 ")
            }
        }
        bw.write("\n")
    }
    bw.flush()
    bw.close()
}
