import java.util.StringTokenizer

/*
* 백준 11660번. 구간 합 구하기 5
* https://www.acmicpc.net/problem/11660
*/

data class Solve(val x1: Int, val y1: Int, val x2: Int, val y2: Int)

private lateinit var num: Array<IntArray>
private lateinit var prefixSum: Array<IntArray>
private val solveList = arrayListOf<Solve>()

private fun main() {

    initVariable()
    getPrefixSum()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    num = Array(size = n) { IntArray(size = n) { 0 } }
    prefixSum = Array(size = n + 1) { IntArray(size = n + 1) { 0 } }

    for (i in 0 until n) {

        val st = StringTokenizer(br.readLine())
        for (j in 0 until n) {
            num[i][j] = st.nextToken().toInt()
        }
    }

    for (i in 0 until m) {
        val line = br.readLine().split(' ').map { it.toInt() }
        solveList.add(Solve(line[0], line[1], line[2], line[3]))
    }

    br.close()
}

private fun getPrefixSum() {

    for (i in num.indices) {
        for (j in num[i].indices) {
            prefixSum[i + 1][j + 1] = prefixSum[i + 1][j] + num[i][j]
        }
    }

    for (i in num.indices) {
        for (j in num[i].indices) {
            prefixSum[i + 1][j + 1] += prefixSum[i][j + 1]
        }
    }
}

private fun getResult(): ArrayList<Int> {

    val result = arrayListOf<Int>()

    solveList.forEach {
        val sum =
            prefixSum[it.x2][it.y2] - prefixSum[it.x2][it.y1 - 1] - prefixSum[it.x1 - 1][it.y2] + prefixSum[it.x1 - 1][it.y1 - 1]
        result.add(sum)
    }

    return result
}

private fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
