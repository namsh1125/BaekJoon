import kotlin.math.pow
import kotlin.math.sqrt

/*
* 백준 4386번. 별자리 만들기
* https://www.acmicpc.net/problem/4386
*/

data class Node(val index: Int, val x: Double, val y: Double)
data class Edge(
    val node1: Node,
    val node2: Node,
    val weight: Double
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return if (weight < other.weight) -1 else 1
    }
}

private fun main() {
    val graph = initVariable()
    val result = getResult(graph)
    printResult(result)
}

private fun initVariable(): ArrayDeque<Node> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val graph = ArrayDeque<Node>()
    repeat(n) { i ->
        val (x, y) = br.readLine().split(' ').map { it.toDouble() }
        graph.add(Node(i, x, y))
    }

    return graph
}

private fun getResult(graph: ArrayDeque<Node>): Double {

    val parent = IntArray(size = graph.size) { it }
    val edgeList = ArrayDeque<Edge>()

    for (i in graph.indices) {
        for (j in i + 1 until graph.size) {
            val node1 = graph[i]
            val node2 = graph[j]

            edgeList.add(Edge(node1, node2, getDistance(node1, node2)))
        }
    }

    edgeList.sort()

    var distance = 0.0
    for (edge in edgeList) {
        val node1 = edge.node1
        val node2 = edge.node2

        if (findParent(node1.index, parent) != findParent(node2.index, parent)) {
            unionParent(node1.index, node2.index, parent)
            distance += edge.weight
        }
    }

    return distance
}

private fun getDistance(node1: Node, node2: Node): Double {
    return sqrt((node1.x - node2.x).pow(2) + (node1.y - node2.y).pow(2))
}

private fun findParent(i: Int, parent: IntArray): Int {
    return if (parent[i] == i) i
    else findParent(parent[i], parent)
}

private fun unionParent(i: Int, j: Int, parent: IntArray) {
    val parent1 = findParent(i, parent)
    val parent2 = findParent(j, parent)

    if (parent1 != parent2) parent[parent2] = parent1
    else parent[parent1] = parent2
}

private fun printResult(result: Double) {
    val bw = System.out.bufferedWriter()
    bw.write(String.format("%.2f", result))
    bw.flush()
    bw.close()
}
