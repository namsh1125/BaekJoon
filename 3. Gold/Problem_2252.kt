/*
* 백준 2252번. 줄 세우기
* https://www.acmicpc.net/problem/2252
*/

private lateinit var inDegree: IntArray
private lateinit var bigger: Array<ArrayList<Int>>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    inDegree = IntArray(n + 1) { 0 }
    bigger = Array(n + 1) { arrayListOf() }
    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        bigger[a].add(b)
        inDegree[b]++
    }

    br.close()
}

private fun getResult(): ArrayList<Int> {
    val result = ArrayList<Int>()
    val queue = ArrayDeque<Int>()

    for (i in 1 until inDegree.size) {
        if (inDegree[i] == 0) queue.add(i)
    }

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        result.add(cur)

        bigger[cur].forEach { next ->
            inDegree[next]--
            if (inDegree[next] == 0) queue.add(next)
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
