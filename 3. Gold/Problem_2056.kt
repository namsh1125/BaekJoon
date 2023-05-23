import java.util.StringTokenizer
import kotlin.math.max

/*
* 백준 2056번. 작업
* https://www.acmicpc.net/problem/2056
*/

data class Job(val index: Int, val time: Int)

private var n = -1
private lateinit var reverseGraph: Array<ArrayList<Int>>
private lateinit var time: IntArray
private lateinit var inDegree: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    reverseGraph = Array(n) { arrayListOf() }
    time = IntArray(n) { 0 }
    inDegree = IntArray(n) { 0 }

    repeat(n) { i ->
        val st = StringTokenizer(br.readLine())
        time[i] = st.nextToken().toInt()

        val count = st.nextToken().toInt()
        for (j in 0 until count) {
            reverseGraph[st.nextToken().toInt() - 1].add(i)
            inDegree[i]++
        }
    }

    br.close()
}

private fun getResult(): Int {
    val startTime = IntArray(n) { 0 }

    val queue = ArrayDeque<Job>()
    for (i in 0 until n) {
        if (inDegree[i] == 0) queue.add(Job(i, 0))
    }

    var result = 0
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (jobIdx in reverseGraph[cur.index]) {
            startTime[jobIdx] = max(startTime[jobIdx], cur.time + time[cur.index])
            inDegree[jobIdx]--

            if (inDegree[jobIdx] == 0) {
                queue.add(Job(jobIdx, startTime[jobIdx]))
            }
        }

        result = max(result, startTime[cur.index] + time[cur.index])
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
