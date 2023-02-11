import java.util.PriorityQueue
import kotlin.math.max

/*
* 백준 1647번. 도시 분할 계획
* https://www.acmicpc.net/problem/1647
*/

data class Edge(
    val node1: Int,
    val node2: Int,
    val cost: Int
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return cost - other.cost
    }
}

private var node = -1
private val edgeList = PriorityQueue<Edge>()
private lateinit var parent: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    repeat(m) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        edgeList.add(Edge(a, b, c))
    }

    node = n
    parent = IntArray(n + 1) { it }

    br.close()
}

private fun getResult(): Int {
    var result = 0
    var maxCost = Int.MIN_VALUE

    while (edgeList.isNotEmpty()) {
        val edge = edgeList.remove()

        if (findParent(edge.node1) != findParent(edge.node2)) {
            unionParent(edge.node1, edge.node2)
            result += edge.cost
            maxCost = max(maxCost, edge.cost)
        }
    }

    return result - maxCost
}

private fun findParent(i: Int): Int {
    return if (parent[i] == i) i
    else findParent(parent[i])
}

private fun unionParent(i: Int, j: Int) {
    val a = findParent(i)
    val b = findParent(j)

    if (a < b) parent[b] = a
    else parent[a] = b
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
