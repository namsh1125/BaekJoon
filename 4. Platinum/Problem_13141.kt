import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/*
* 백준 13141번. Ignition
* https://www.acmicpc.net/problem/13141
*/

data class Edge(val node1: Int, val node2: Int, val dist: Int)

private var n = -1 // 정점의 수
private var m = -1 // 간선의 수
private val edgeList = ArrayDeque<Edge>()

private const val INF = 20_000 * 100 + 1
private lateinit var dist: Array<IntArray>

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

    dist = Array(n) { IntArray(n) { INF } }

    for (i in 0 until n) {
        dist[i][i] = 0
    }

    repeat(m) {
        val st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt() - 1
        val e = st.nextToken().toInt() - 1
        val l = st.nextToken().toInt()

        edgeList.add(Edge(s, e, l))
        dist[s][e] = min(dist[s][e], l)
        dist[e][s] = min(dist[e][s], l)
    }

    br.close()
}

private fun getResult(): String {
    floydWarshall()

    var result = Double.MAX_VALUE
    for (i in 0 until n) {
        result = min(result, burn(i))
    }

    return String.format("%.1f", result)
}

private fun floydWarshall() {
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }
}

private fun burn(node: Int): Double {
    val time = minTime(node) // 최초로 불이 노드에 도착하는 시간

    var result = 0.0
    val queue = copyEdge()

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        result = max(result, burnTime(time[cur.node1], time[cur.node2], cur.dist))
    }

    return result
}

private fun minTime(node: Int): IntArray {
    val time = IntArray(n) { Int.MAX_VALUE }

    for (i in 0 until n) {
        time[i] = dist[node][i]
    }

    return time
}

private fun copyEdge(): ArrayDeque<Edge> {
    val copy = ArrayDeque<Edge>()

    for (i in edgeList.indices) {
        copy.add(edgeList[i])
    }

    return copy
}

private fun burnTime(time1: Int, time2: Int, dist: Int): Double {
    val minTime = min(time1, time2)
    val maxTime = max(time1, time2)

    return if (minTime + dist <= maxTime) maxTime.toDouble()
    else maxTime + (dist - (maxTime - minTime)) / 2.0
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}