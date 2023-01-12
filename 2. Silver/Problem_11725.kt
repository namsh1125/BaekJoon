/*
* 백준 11725번. 트리의 부모 찾기
* https://www.acmicpc.net/problem/11725
*/

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var parent: IntArray

fun main() {

    initVariable()
    getResult()
    printResult()
}

fun initVariable() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()

    graph = Array(size = n + 1) { ArrayList() }
    parent = IntArray(size = n + 1) { -1 }

    repeat(n - 1) {
        val (a, b) = readLine().split(' ').map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }
}

fun getResult() {

    val visited = BooleanArray(size = graph.size) { false }

    val queue = ArrayDeque<Int>()
    queue.add(1)
    visited[1] = true

    while(queue.isNotEmpty()) {

        val top = queue.removeFirst()

        graph[top].forEach {
            if(!visited[it]) {
                parent[it] = top
                visited[it] = true
                queue.add(it)
            }
        }
    }
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    for(i in 2 until parent.size) {
        bw.write("${parent[i]}\n")
    }
    bw.flush()
    bw.close()
}
