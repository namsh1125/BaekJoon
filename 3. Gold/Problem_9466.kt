/*
* 백준 9466번. 텀 프로젝트
* https://www.acmicpc.net/problem/9466
*/

private fun main() {
    val result = ArrayList<Int>()
    val br = System.`in`.bufferedReader()

    val t = br.readLine().toInt()
    repeat(t) {
        val n = br.readLine().toInt()

        val graph = Array(n) { -1 }
        br.readLine().split(' ').forEachIndexed { index, s ->
            graph[index] = s.toInt() - 1
        }

        result.add(getResult(graph))
    }
    br.close()

    printResult(result)
}

private fun getResult(graph: Array<Int>): Int {
    val visited = BooleanArray(graph.size) { false }
    val finished = BooleanArray(graph.size) { false }
    var count = 0

    fun dfs(index: Int) {

        if (!visited[index]) {
            visited[index] = true
            dfs(graph[index])
        } else {
            if (finished[index]) return
            else {
                var next = graph[index]
                while (next != index) {
                    count++
                    next = graph[next]
                }
                count++

                return
            }
        }

        finished[index] = true
    }

    for (i in graph.indices) {
        if (!visited[i]) dfs(i)
    }

    return graph.size - count
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
