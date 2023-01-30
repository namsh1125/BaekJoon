import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.min

/*
* 백준 1504번. 특정한 최단 경로
* https://www.acmicpc.net/problem/1504
*/

data class Node(
    val node: Int,
    val weight: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return weight - other.weight
    }
}

private lateinit var graph: Array<ArrayList<Node>>
private val nodeToVisit = arrayListOf<Int>()
private const val INF = 99_999_999

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, e) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(size = n + 1) { arrayListOf() }
    repeat(e) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Node(b, c))
        graph[b].add(Node(a, c))
    }

    val st = StringTokenizer(br.readLine())
    nodeToVisit.add(st.nextToken().toInt())
    nodeToVisit.add(st.nextToken().toInt())

    br.close()
}

private fun getResult(): Int {
    val dis1 = dijkstra(1) // 1번 정점에 대해 다익스트라 알고리즘을 돌린 경우
    val dis2 = dijkstra(graph.size - 1) // N번 정점에 대해 다익스트라 알고리즘을 돌린 경우

    val node1 = nodeToVisit[0]
    val node2 = nodeToVisit[1]
    val weight = dijkstra(node1)[node2] // 방문해야 하는 두 정점 사이의 가중치

    val result = min(dis1[node1] + weight + dis2[node2], dis1[node2] + weight + dis2[node1])
    return if (result >= INF) -1 else result
}

private fun dijkstra(start: Int): IntArray {

    val visited = BooleanArray(size = graph.size) { false }
    val distance = IntArray(size = graph.size) { INF }
    distance[start] = 0

    val queue = PriorityQueue<Node>()
    queue.add(Node(start, 0))

    while (queue.isNotEmpty()) {
        val current = queue.remove()

        if (visited[current.node]) continue
        else visited[current.node] = true

        for (neighbor in graph[current.node]) {
            if (distance[neighbor.node] > distance[current.node] + neighbor.weight) {
                distance[neighbor.node] = distance[current.node] + neighbor.weight
                queue.add(Node(neighbor.node, distance[neighbor.node]))
            }
        }
    }

    return distance
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
