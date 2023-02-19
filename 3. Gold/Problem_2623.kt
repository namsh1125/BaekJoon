/*
* 백준 2623번. 음악프로그램
* https://www.acmicpc.net/problem/2623
*/

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var inDegree: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(n + 1) { arrayListOf() }
    inDegree = IntArray(n + 1) { 0 }

    repeat(m) {
        val line = br.readLine().split(' ').map { it.toInt() }
        val k = line[0]

        for (i in 2..k) {
            val a = line[i - 1]
            val b = line[i]

            graph[a].add(b)
            inDegree[b]++
        }
    }

    br.close()
}

private fun getResult(): ArrayList<Int> {
    val result = ArrayList<Int>()
    val queue = ArrayDeque<Int>()

    for (i in 1 until graph.size) {
        if (inDegree[i] == 0) queue.add(i)
    }

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        result.add(cur)

        for (neighbor in graph[cur]) {
            inDegree[neighbor]--

            if (inDegree[neighbor] == 0) {
                queue.add(neighbor)
            }
        }
    }

    return if (result.size == graph.size - 1) {
        result
    } else {
        arrayListOf(0)
    }
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
