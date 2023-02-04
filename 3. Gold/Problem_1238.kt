import java.util.PriorityQueue
import kotlin.math.max

/*
* 백준 1238번. 파티
* https://www.acmicpc.net/problem/1238
*/

data class Node(
    val node: Int,
    val time: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return time - other.time
    }
}

private lateinit var graph: Array<ArrayList<Node>>
private lateinit var reverseGraph: Array<ArrayList<Node>>
private var meet = -1

private const val INF = 1000 * 100 + 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m, x) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(size = n + 1) { arrayListOf() }
    reverseGraph = Array(size = n + 1) { arrayListOf() }
    repeat(m) {
        val (start, end, time) = br.readLine().split(' ').map { it.toInt() }
        graph[start].add(Node(end, time))
        reverseGraph[end].add(Node(start, time))
    }

    meet = x

    br.close()
}

private fun getResult(): Int {
    val time = dijkstra(graph, meet)
    val reverseTime = dijkstra(reverseGraph, meet)

    var result = Int.MIN_VALUE
    for (i in 1 until graph.size) {
        result = max(result, time[i] + reverseTime[i])
    }

    return result
}

private fun dijkstra(graph: Array<ArrayList<Node>>, start: Int): IntArray {
    val time = IntArray(graph.size) { INF }
    time[start] = 0

    val queue = PriorityQueue<Node>()
    queue.add(Node(start, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (neighbor in graph[cur.node]) {
            if (time[neighbor.node] > time[cur.node] + neighbor.time) {
                time[neighbor.node] = time[cur.node] + neighbor.time
                queue.add(Node(neighbor.node, cur.time + neighbor.time))
            }
        }
    }

    return time
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
