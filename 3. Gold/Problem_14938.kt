import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

/*
* 백준 14938번. 서강그라운드
* https://www.acmicpc.net/problem/14938
*/

data class Node(
    val node: Int,
    val distance: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return distance - other.distance
    }
}

private lateinit var graph: Array<ArrayList<Node>>
private lateinit var item: IntArray
private var canGo = -1

private const val INF = 100 * 15 + 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m, r) = br.readLine().split(' ').map { it.toInt() }

    canGo = m

    item = IntArray(n + 1) { -1 }
    val st = StringTokenizer(br.readLine())
    for (i in 1..n) {
        item[i] = st.nextToken().toInt()
    }

    graph = Array(n + 1) { arrayListOf() }
    repeat(r) {
        val (a, b, l) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Node(b, l))
        graph[b].add(Node(a, l))
    }

    br.close()
}

private fun getResult(): Int {
    var result = 0

    for (i in 1 until graph.size) {
        val distance = dijkstra(i)

        var eat = 0
        for (j in 1 until graph.size) {
            if (distance[j] <= canGo) {
                eat += item[j]
            }
        }

        result = max(result, eat)
    }

    return result
}

private fun dijkstra(start: Int): IntArray {
    val distance = IntArray(graph.size) { INF }
    distance[start] = 0

    val queue = PriorityQueue<Node>()
    queue.add(Node(start, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (neighbor in graph[cur.node]) {
            if (distance[neighbor.node] > distance[cur.node] + neighbor.distance) {
                distance[neighbor.node] = distance[cur.node] + neighbor.distance
                queue.add(Node(neighbor.node, distance[neighbor.node]))
            }
        }
    }

    return distance
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
