import kotlin.math.max

/*
* 백준 12865번. 평범한 배낭
* https://www.acmicpc.net/problem/12865
*/

data class Thing(val w: Int, val v: Int)

private val thingList = ArrayDeque<Thing>()
private var max = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()

    val (n, k) = br.readLine().split(' ').map { it.toInt() }

    thingList.add(Thing(0, 0))
    repeat(n) {
        val (w, v) = br.readLine().split(' ').map { it.toInt() }
        thingList.add(Thing(w, v))
    }

    max = k
}

fun getResult(): Int {

    val dp = Array(size = thingList.size + 1) { IntArray(size = max + 1) { 0 } }

    for (i in 1 until thingList.size) {
        for (j in 1..max) {

            val thing = thingList[i]
            if (j - thing.w >= 0) {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - thing.w] + thing.v)
            } else {
                dp[i][j] = dp[i - 1][j]
            }
        }
    }

    return dp[thingList.size - 1][max]
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
