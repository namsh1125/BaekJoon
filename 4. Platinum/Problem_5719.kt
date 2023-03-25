/*
* 백준 5719번. 거의 최단 경로
* https://www.acmicpc.net/problem/5719
*/

data class Node(val node: Int, val weight: Int)

const val INF = 500 * 10000 + 1

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = StringBuilder()

    while (true) {
        val (n, m) = br.readLine().split(' ').map { it.toInt() }
        if (n == 0 && m == 0) break
        val graph = Array(n) { ArrayList<Node>() }
        val reversedGraph = Array(n) { ArrayList<Node>() }

        val (s, d) = br.readLine().split(' ').map { it.toInt() }

        repeat(m) {
            val (u, v, p) = br.readLine().split(' ').map { it.toInt() }
            graph[u].add(Node(v, p))
            reversedGraph[v].add(Node(u, p))
        }

        result.append("${getResult(graph, reversedGraph, s, d)}\n")
    }
    br.close()

    printResult(result.toString())
}

private fun getResult(graph: Array<ArrayList<Node>>, reversedGraph: Array<ArrayList<Node>>, start: Int, end: Int): Int {
    val dist = dijkstra(graph, start)
    deleteShortestDistance(reversedGraph, dist, end)
    val newDist = dijkstra(reversedGraph, end)

    return if (newDist[start] == INF) -1 else newDist[start]
}

private fun dijkstra(graph: Array<ArrayList<Node>>, start: Int): IntArray {
    val dist = IntArray(graph.size) { INF }
    dist[start] = 0

    val queue = ArrayDeque<Node>()
    queue.add(Node(start, 0))
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (neighbor in graph[cur.node]) {
            if (dist[neighbor.node] > cur.weight + neighbor.weight) {
                dist[neighbor.node] = cur.weight + neighbor.weight
                queue.add(Node(neighbor.node, dist[neighbor.node]))
            }
        }
    }

    return dist
}

private fun deleteShortestDistance(reversedGraph: Array<ArrayList<Node>>, dist: IntArray, end: Int) {
    val queue = ArrayDeque<Node>()
    queue.add(Node(end, dist[end]))

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        val removeList = arrayListOf<Int>()

        for (i in reversedGraph[cur.node].indices) {
            val neighbor = reversedGraph[cur.node][i]

            if (cur.weight - neighbor.weight == dist[neighbor.node]) {
                removeList.add(i)
                queue.add(Node(neighbor.node, cur.weight - neighbor.weight))
            }
        }

        for (i in removeList.lastIndex downTo 0) {
            reversedGraph[cur.node].removeAt(removeList[i])
        }
    }
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}
