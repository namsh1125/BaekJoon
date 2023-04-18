import java.util.PriorityQueue
import kotlin.math.roundToInt
import kotlin.math.sqrt

/*
* 백준 1774번. 우주신과의 교감
* https://www.acmicpc.net/problem/1774
*/

data class Point(val x: Double, val y: Double)
data class Edge(
    val point1Index: Int,
    val point2Index: Int,
    val dist: Double
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return ((dist - other.dist) * 100).roundToInt() // 소수점 이하도 판별하기 위해 * 100 e.g.) 1.414 vs 1.515
    }
}

private var n = -1
private var m = -1

private val pointList = ArrayDeque<Point>()
private lateinit var parent: IntArray

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
    }

    repeat(n) {
        val (x, y) = br.readLine().split(' ').map { it.toDouble() }
        pointList.add(Point(x, y))
    }

    parent = IntArray(n + 1) { it }
    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() - 1 }
        unionParent(a, b)
    }

    br.close()
}

private fun getResult(): String {
    val edgeList = PriorityQueue<Edge>()

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (findParent(i) != findParent(j)) {
                edgeList.add(Edge(i, j, distance(pointList[i], pointList[j])))
            }
        }
    }

    var result = 0.0
    while (edgeList.isNotEmpty()) {
        val cur = edgeList.remove()
        val index1 = cur.point1Index
        val index2 = cur.point2Index

        if (findParent(index1) != findParent(index2)) {
            result += cur.dist
            unionParent(index1, index2)
        }
    }

    return String.format("%.2f", result)
}

private fun distance(point1: Point, point2: Point): Double {
    return sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y))
}

private fun findParent(i: Int): Int {
    return if (parent[i] == i) i
    else {
        parent[i] = findParent(parent[i])
        parent[i]
    }
}

private fun unionParent(i: Int, j: Int) {
    val par1 = findParent(i)
    val par2 = findParent(j)

    if (par1 < par2) parent[par2] = par1
    else parent[par1] = par2
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result + "\n")
    bw.flush()
    bw.close()
}
