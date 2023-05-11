import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min

/*
* 백준 3176번. 도로 네트워크
* https://www.acmicpc.net/problem/3176
*/

data class Edge(val node: Int, val distance: Int)
data class Tc(val node1: Int, val node2: Int)

private var n = -1
private lateinit var graph: Array<ArrayList<Edge>>

private var maxDepth = -1
private lateinit var depth: IntArray
private lateinit var parent: Array<IntArray> // i번째 node의 2^j의 parent를 담고 있는 배열

private lateinit var min: Array<IntArray> // i번째 node의 2^j의 parent node까지 가는 거리 중 최소 길이를 담고 있는 배열
private lateinit var max: Array<IntArray> // i번째 node의 2^j의 parent node까지 가는 거리 중 최장 길이를 담고 있는 배열

private const val NONE = 0
private const val INF = 1_000_001

private var k = -1
private val tcList = ArrayList<Tc>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    graph = Array(n + 1) { arrayListOf() }
    repeat(n - 1) {
        val (a, b, c) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Edge(b, c))
        graph[b].add(Edge(a, c))
    }

    k = br.readLine().toInt()
    repeat(k) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        tcList.add(Tc(a, b))
    }

    br.close()
}

private fun getResult(): String {
    makeTree()

    return buildString {
        for (tc in tcList) {
            appendLine("${getResult(tc.node1, tc.node2)}")
        }
    }
}

private fun makeTree() {
    depth = IntArray(n + 1) { -1 }
    depth[1] = 1 // 임의로 node1번이 root라고 가정

    maxDepth = log2(n.toDouble()).toInt() + 1
    parent = Array(n + 1) { IntArray(maxDepth) { NONE } }

    min = Array(n + 1) { IntArray(maxDepth) { INF } }
    max = Array(n + 1) { IntArray(maxDepth) { -1 } }

    val queue: Queue<Int> = LinkedList()
    queue.add(1)

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (neighbor in graph[cur]) {
            if (depth[neighbor.node] == -1) {
                parent[neighbor.node][0] = cur // 자신의 부모 설정
                depth[neighbor.node] = depth[cur] + 1 // 자신의 깊이 설정

                min[neighbor.node][0] = neighbor.distance // 최단 거리 설정
                max[neighbor.node][0] = neighbor.distance // 최장 거리 설정

                queue.add(neighbor.node)
            }
        }
    }

    update()
}

private fun update() {
    for (d in 1 until maxDepth) {
        for (i in 2..n) { // 1번 노드는 루트라 패스
            if (parent[parent[i][d - 1]][d - 1] != NONE) {
                parent[i][d] = parent[parent[i][d - 1]][d - 1]
                min[i][d] = min(min[i][d - 1], min[parent[i][d - 1]][d - 1])
                max[i][d] = max(max[i][d - 1], max[parent[i][d - 1]][d - 1])
            }
        }
    }
}

private fun getResult(node1: Int, node2: Int): String {
    var shallowNode: Int
    var deepNode: Int

    if (depth[node1] < depth[node2]) {
        shallowNode = node1
        deepNode = node2
    } else {
        shallowNode = node2
        deepNode = node1
    }

    var minD = INF // 최소 거리
    var maxD = 0 // 최대 거리

    // 깊이 맞추기
    for (d in maxDepth - 1 downTo 0) {
        if (depth[deepNode] - depth[shallowNode] >= (1 shl d)) {
            minD = min(minD, min[deepNode][d])
            maxD = max(maxD, max[deepNode][d])

            deepNode = parent[deepNode][d]
        }
    }

    // lca까지 이동
    while (shallowNode != deepNode) {
        for (i in maxDepth - 1 downTo 0) {
            if (parent[shallowNode][i] != parent[deepNode][i]) {
                minD = min(minD, min(min[shallowNode][i], min[deepNode][i]))
                maxD = max(maxD, max(max[shallowNode][i], max[deepNode][i]))

                shallowNode = parent[shallowNode][i]
                deepNode = parent[deepNode][i]
            }
        }

        minD = min(minD, min(min[shallowNode][0], min[deepNode][0]))
        maxD = max(maxD, max(max[shallowNode][0], max[deepNode][0]))

        shallowNode = parent[shallowNode][0]
        deepNode = parent[deepNode][0]
    }


    return "$minD $maxD"
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}
