/*
* 백준 1260번. DFS와 BFS
* https://www.acmicpc.net/problem/1260
*/

data class Result(val dfs: ArrayList<Int>, val bfs: ArrayList<Int>)

private lateinit var graph: Array<MutableList<Int>>
private var start = -1

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m, v) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(size = n + 1) { mutableListOf() }
    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    start = v
}

fun getResult(): Result {

    val dfs = dfs()
    val bfs = bfs()

    return Result(dfs, bfs)
}

fun dfs(): ArrayList<Int> {

    graph.forEach {
        it.sortDescending()
    }

    val result = arrayListOf<Int>()
    val stack = ArrayDeque<Int>()
    val visited = BooleanArray(size = graph.size) { false }

    result.add(start)
    stack.add(start)
    visited[start] = true

    while (stack.isNotEmpty()) {

        val top = stack.removeLast()
        if (!visited[top]) {
            result.add(top)
            visited[top] = true
        }

        graph[top].forEach {
            if (!visited[it]) {
                stack.add(it)
            }
        }
    }

    return result
}

fun bfs(): ArrayList<Int> {

    graph.forEach {
        it.sort()
    }

    val result = arrayListOf<Int>()
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(size = graph.size) { false }

    result.add(start)
    queue.add(start)
    visited[start] = true
    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()
        graph[top].forEach {
            if (!visited[it]) {
                queue.add(it)
                result.add(it)
                visited[it] = true
            }
        }
    }

    return result
}

fun printResult(result: Result) {

    val bw = System.out.bufferedWriter()

    result.dfs.forEach {
        bw.write("$it ")
    }
    bw.write("\n")
    result.bfs.forEach {
        bw.write("$it ")
    }

    bw.flush()
    bw.close()
}
