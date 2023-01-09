/*
* 백준 11724번. 연결 요소의 개수
* https://www.acmicpc.net/problem/11724
*/

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var visited: BooleanArray

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable(): Array<ArrayList<Int>> {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(size = n + 1) { arrayListOf() }
    visited = BooleanArray(size = n + 1) { false }

    repeat(m) {

        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    return graph
}

fun getResult(): Int {

    var result = 0
    for (i in 1 until graph.size) {
        if (!visited[i]) {
            dfs(i)
            result++
        }
    }

    return result
}

fun dfs(now: Int) {

    visited[now] = true
    for (i in graph[now]) {
        if (!visited[i]) {
            dfs(i)
        }
    }
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
