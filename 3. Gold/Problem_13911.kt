import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.min

/*
* 백준 13911번. 집 구하기
* https://www.acmicpc.net/problem/13911
*/

data class Edge(
    val node: Int,
    val dist: Int
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return dist - other.dist
    }
}

private var v = -1 // 정점의 개수
private var e = -1 // 도로의 개수
private lateinit var graph: Array<ArrayList<Edge>>

private var m = -1 // 맥도날드의 수
private var x = -1 // 맥세권 조건
private val mcDonaldList = ArrayList<Int>()

private var s = -1 // 스타벅스의 수
private var y = -1 // 스세권 조건
private val starBucksList = ArrayList<Int>()

private var dummyMc = -1 // 맥도날드 더미 노드
private var dummyStarBucks = -1 // 스타벅스 더미 노드

private const val INF = 100_000_001

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(' ').map { it.toInt() }.also {
        v = it[0]
        e = it[1]
    }

    dummyMc = v + 1
    dummyStarBucks = v + 2

    graph = Array(v + 3) { arrayListOf() }
    repeat(e) {
        val (u, v, w) = br.readLine().split(' ').map { it.toInt() }
        graph[u].add(Edge(v, w))
        graph[v].add(Edge(u, w))
    }

    var st = StringTokenizer(br.readLine())
    m = st.nextToken().toInt()
    x = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    repeat(m) {
        mcDonaldList.add(st.nextToken().toInt())
    }

    st = StringTokenizer(br.readLine())
    s = st.nextToken().toInt()
    y = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    repeat(s) {
        starBucksList.add(st.nextToken().toInt())
    }

    br.close()
}

private fun getResult(): Int {
    connectDummyNode()// 가상의 노드와 연결

    val mcDist = dijkstra(dummyMc)
    val starBucksDist = dijkstra(dummyStarBucks)

    var result = 2 * INF
    for (i in 1..v) {
        if (mcDonaldList.contains(i) || starBucksList.contains(i)) continue // 정점이 맥도날드거나 스타벅스인 경우 통과
        if (mcDist[i] > x || starBucksDist[i] > y) continue // 맥세권, 스세권이 아닌 경우 통과
        result = min(result, mcDist[i] + starBucksDist[i])
    }

    return if (result == 2 * INF) -1 else result
}

private fun connectDummyNode() {
    mcDonaldList.forEach { pos ->
        graph[dummyMc].add(Edge(pos, 0))
        graph[pos].add(Edge(dummyMc, 0))
    }

    starBucksList.forEach { pos ->
        graph[dummyStarBucks].add(Edge(pos, 0))
        graph[pos].add(Edge(dummyStarBucks, 0))
    }
}

private fun dijkstra(start: Int): IntArray {
    val dist = IntArray(v + 3) { INF }
    dist[start] = 0

    val queue = PriorityQueue<Edge>()
    queue.add(Edge(start, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        if (cur.dist >= INF) break

        for (edge in graph[cur.node]) {
            if (edge.node == dummyMc || edge.node == dummyStarBucks) continue
            if (dist[edge.node] > cur.dist + edge.dist) {
                dist[edge.node] = cur.dist + edge.dist
                queue.add(Edge(edge.node, dist[edge.node]))
            }
        }
    }

    return dist
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
