import java.util.PriorityQueue

/*
* 백준 20007번. 떡 돌리기
* https://www.acmicpc.net/problem/20007
*/

data class Edge(
    val node: Int,
    val dist: Int
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return dist - other.dist
    }
}

private var n = -1 // 집의 개수
private lateinit var graph: Array<ArrayList<Edge>>

private var m = -1 // 양방향 도로의 개수
private var x = -1 // 최대 거리
private var y = -1 // 성현이의 집

private const val INF = 10_000_001

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
        x = it[2]
        y = it[3]
    }

    graph = Array(n) { arrayListOf() }
    repeat(m) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Edge(b, c))
        graph[b].add(Edge(a, c))
    }

    br.close()
}

private fun getResult(): Int {
    val dist = getRoundTripDistance(dijkstra())
    dist.sort()

    if (dist.last() > x) return -1

    var day = 0
    while (dist.isNotEmpty()) {
        var today = dist.removeFirst()

        while (dist.isNotEmpty() && today + dist.first() <= x) {
            today += dist.removeFirst()
        }

        day++
    }

    return day
}

private fun dijkstra(): IntArray {
    val dist = IntArray(n) { INF }
    dist[y] = 0

    val queue = PriorityQueue<Edge>()
    queue.add(Edge(y, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (edge in graph[cur.node]) {
            if (dist[edge.node] > cur.dist + edge.dist) {
                dist[edge.node] = cur.dist + edge.dist
                queue.add(Edge(edge.node, dist[edge.node]))
            }
        }
    }

    return dist
}

private fun getRoundTripDistance(dist: IntArray): ArrayDeque<Int> {
    val result = ArrayDeque<Int>()

    for (i in dist.indices) {
        if (i == y) continue // 자기 집은 패스
        result.add(dist[i] * 2)
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
