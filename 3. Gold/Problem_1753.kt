import kotlin.math.min
import java.util.PriorityQueue

/*
* 백준 1753번. 최단경로
* https://www.acmicpc.net/problem/1753
*/

data class Node(
    val connectedNode: Int,
    val weight: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return weight - other.weight
    }
}

private lateinit var graph: Array<ArrayList<Node>>
private lateinit var distance: IntArray
private var start = -1
const val INF = 999_999_999

fun main() {

    initVariable()
    dijkstra()
    printResult()
}

fun initVariable() = with(System.`in`.bufferedReader()) {

    val (v, e) = readLine().split(' ').map { it.toInt() }

    start = readLine().toInt()

    distance = IntArray(size = v + 1) { INF }
    distance[start] = 0

    graph = Array(size = v + 1) { ArrayList() }
    repeat(e) {
        val (u, v, w) = readLine().split(' ').map { it.toInt() }
        graph[u].add(Node(v, w))
    }
}

fun dijkstra() {

    val visited = BooleanArray(size = graph.size) { false }

    val queue = PriorityQueue<Node>()
    queue.add(Node(start, 0))

    while (queue.isNotEmpty()) {

        val top = queue.remove()
        val currentNode = top.connectedNode

        if (visited[currentNode]) continue
        else {
            visited[currentNode] = true

            graph[currentNode].forEach {
                val nextNode = it.connectedNode

                if (!visited[nextNode]) {
                    distance[nextNode] = min(distance[nextNode], distance[currentNode] + it.weight)
                    queue.add(Node(nextNode, distance[nextNode]))
                }
            }
        }
    }

}

fun printResult() {

    val bw = System.out.bufferedWriter()
    for (i in 1 until distance.size) {
        if (distance[i] == INF) bw.write("INF\n")
        else bw.write("${distance[i]}\n")
    }
    bw.flush()
    bw.close()
}
