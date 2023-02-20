import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/*
* 백준 1005번. ACM Craft
* https://www.acmicpc.net/problem/1005
*/

data class Construct(val num: Int, val time: Int)

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = arrayListOf<Int>()

    val t = br.readLine().toInt()
    repeat(t) {
        val (n, k) = br.readLine().split(' ').map { it.toInt() }
        val building = Array(n + 1) { ArrayList<Int>() }
        val inverse = Array(n + 1) { ArrayList<Int>() }
        val inDegree = IntArray(n + 1) { 0 }
        val time = IntArray(n + 1) { 0 }

        val st = StringTokenizer(br.readLine())
        for (i in 1..n) {
            time[i] = st.nextToken().toInt()
        }

        repeat(k) {
            val (x, y) = br.readLine().split(' ').map { it.toInt() }
            building[x].add(y)
            inverse[y].add(x)
            inDegree[y]++
        }

        val final = br.readLine().toInt()

        result.add(getResult(building, inverse, inDegree, time, final))
    }

    printResult(result)
}

private fun getResult(building: Array<ArrayList<Int>>, inverse: Array<ArrayList<Int>>, inDegree: IntArray, time: IntArray, final: Int): Int {
    val queue = ArrayDeque<Construct>()

    for (i in 1 until building.size) {
        if (inDegree[i] == 0) queue.add(Construct(i, 0))
    }

    val dp = IntArray(building.size) { Int.MAX_VALUE }
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        val time = cur.time + time[cur.num]
        dp[cur.num] = min(dp[cur.num], time)

        for (next in building[cur.num]) {
            inDegree[next]--

            if (inDegree[next] == 0) {
                var leastTime = 0
                for (i in inverse[next]) {
                    leastTime = max(leastTime, dp[i])
                }

                queue.add(Construct(next, leastTime))
            }
        }
    }

    return dp[final]
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
