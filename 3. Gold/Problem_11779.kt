import java.util.PriorityQueue
import java.util.StringTokenizer

/*
* 백준 11779번. 최소비용 구하기 2
* https://www.acmicpc.net/problem/11779
*/

data class Node(
    val node: Int,
    val weight: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return weight - other.weight
    }
}

data class Result(val distance: Int, val route: List<Int>)

private lateinit var graph: Array<ArrayDeque<Node>>
private var start = -1
private var end = -1
private const val INF = 999_999_999

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    graph = Array(size = n + 1) { ArrayDeque() }

    val m = br.readLine().toInt()
    repeat(m) {
        val (s, e, w) = br.readLine().split(' ').map { it.toInt() }
        graph[s].add(Node(e, w))
    }

    val st = StringTokenizer(br.readLine())
    start = st.nextToken().toInt()
    end = st.nextToken().toInt()

    br.close()
}

private fun getResult(): Result {

    val visited = BooleanArray(size = graph.size) { false }
    val parent = IntArray(size = graph.size) { it }
    val distance = IntArray(size = graph.size) { INF }
    distance[start] = 0

    val queue = PriorityQueue<Node>()
    queue.add(Node(start, end))

    while (queue.isNotEmpty()) {
        val current = queue.remove()

        if (!visited[current.node]) visited[current.node] = true
        else continue

        for (neighbor in graph[current.node]) {
            if (distance[neighbor.node] > distance[current.node] + neighbor.weight) {
                distance[neighbor.node] = distance[current.node] + neighbor.weight
                parent[neighbor.node] = current.node
                queue.add(Node(neighbor.node, distance[neighbor.node]))
            }
        }
    }

    val route = ArrayDeque<Int>()
    var current = end
    while (current != start) {
        route.add(current)
        current = parent[current]
    }
    route.add(start)

    return Result(distance[end], route.reversed())
}

private fun printResult(result: Result) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.distance}\n")
    bw.write("${result.route.size}\n")
    result.route.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
