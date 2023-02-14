import java.util.PriorityQueue

/*
* 백준 1766번. 문제집
* https://www.acmicpc.net/problem/1766
*/

private lateinit var problemList: Array<ArrayList<Int>>
private lateinit var indegree: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    problemList = Array(n + 1) { arrayListOf() }
    indegree = IntArray(n + 1) { 0 }

    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        problemList[a].add(b)
        indegree[b]++
    }

    br.close()
}

private fun getResult(): ArrayList<Int> {
    val result = ArrayList<Int>()
    val queue = PriorityQueue<Int>()

    for (i in 1 until problemList.size) {
        if (indegree[i] == 0) queue.add(i)
    }

    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        result.add(cur)

        for (next in problemList[cur]) {
            indegree[next]--

            if (indegree[next] == 0) {
                queue.add(next)
            }
        }
    }

    return result
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
