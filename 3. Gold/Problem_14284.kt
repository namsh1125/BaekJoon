import java.util.PriorityQueue

/*
* 백준 14284번. 간선 이어가기 2
* https://www.acmicpc.net/problem/14284
*/

data class Edge(
        val node: Int,
        val weight: Int
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return weight - other.weight
    }
}

private const val INF = 100_000 * 100 + 1
private lateinit var graph: Array<ArrayList<Edge>>

private var n = -1 // 정점의 개수
private var m = -1 // 간선 개수

private var s = -1
private var t = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        m = it[1]
    }

    graph = Array(n + 1) { arrayListOf() }
    repeat(m) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Edge(b, c))
        graph[b].add(Edge(a, c))
    }

    br.readLine().split(' ').map { it.toInt() }.also {
        s = it[0]
        t = it[1]
    }

    br.close()
}

private fun getResult(): Int {
    val dist = IntArray(n + 1) { INF }
    val queue = PriorityQueue<Edge>()
    queue.add(Edge(s, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (edge in graph[cur.node]) {
            if (cur.weight + edge.weight < dist[edge.node]) {
                dist[edge.node] = cur.weight + edge.weight
                queue.add(Edge(edge.node, dist[edge.node]))
            }
        }
    }

    return dist[t]
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
