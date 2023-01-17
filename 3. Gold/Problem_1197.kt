import java.util.PriorityQueue

/*
* 백준 1197번. 최소 스패닝 트리
* https://www.acmicpc.net/problem/1197
*/

data class Edge(
    val node1: Int,
    val node2: Int,
    val weight: Int
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return weight - other.weight
    }
}

private lateinit var parent: IntArray
private val edgeList = PriorityQueue<Edge>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()

    val (v, e) = br.readLine().split(' ').map { it.toInt() }

    parent = IntArray(size = v + 1) { it }
    repeat(e) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        edgeList.add(Edge(a, b, c))
    }
}

private fun getResult(): Int {

    var weight = 0

    while (edgeList.isNotEmpty()) {
        val edge = edgeList.remove()

        if (findParent(edge.node1) != findParent(edge.node2)) { // 사이클 X
            union(edge.node1, edge.node2)
            weight += edge.weight
        }
    }

    return weight
}

private fun findParent(node: Int): Int {
    if (parent[node] == node) return node
    return findParent(parent[node])
}

private fun union(node1: Int, node2: Int) {
    val parent1 = findParent(node1)
    val parent2 = findParent(node2)

    if (parent1 < parent2) parent[parent2] = parent1
    else parent[parent1] = parent2
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
