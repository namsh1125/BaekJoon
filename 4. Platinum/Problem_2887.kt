/*
* 백준 2887번. 행성 터널
* https://www.acmicpc.net/problem/2887
*/

data class Position(
    val index: Int,
    val value: Int
) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        return value - other.value
    }
}

data class Edge(
    val distance: Int,
    val planet1: Int,
    val planet2: Int
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return distance - other.distance
    }
}

private var n = -1
private val positionX = ArrayDeque<Position>()
private val positionY = ArrayDeque<Position>()
private val positionZ = ArrayDeque<Position>()

private lateinit var parent: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    n = br.readLine().toInt()
    repeat(n) { index ->
        val (x, y, z) = br.readLine().split(' ').map { it.toInt() }
        positionX.add(Position(index, x))
        positionY.add(Position(index, y))
        positionZ.add(Position(index, z))
    }

    parent = IntArray(n) { it }

    br.close()
}

private fun getResult(): Long {
    positionX.sort()
    positionY.sort()
    positionZ.sort()

    val edgeList = ArrayDeque<Edge>()
    for (i in 0 until n - 1) {
        edgeList.add(Edge(positionX[i + 1].value - positionX[i].value, positionX[i].index, positionX[i + 1].index))
        edgeList.add(Edge(positionY[i + 1].value - positionY[i].value, positionY[i].index, positionY[i + 1].index))
        edgeList.add(Edge(positionZ[i + 1].value - positionZ[i].value, positionZ[i].index, positionZ[i + 1].index))
    }
    edgeList.sort()

    var distance = 0L
    edgeList.forEach { edge ->
        val planet1 = edge.planet1
        val planet2 = edge.planet2

        if (findParent(planet1) != findParent(planet2)) {
            unionParent(planet1, planet2)
            distance += edge.distance
        }
    }

    return distance
}

private fun findParent(i: Int): Int {
    return if (parent[i] == i) i
    else findParent(parent[i])
}

private fun unionParent(i: Int, j: Int) {
    val parentI = findParent(i)
    val parentJ = findParent(j)

    if (parentI < parentJ) parent[parentJ] = parentI
    else parent[parentI] = parentJ
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
