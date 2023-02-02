import kotlin.math.log2

/*
* 백준 1761번. 정점들의 거리
* https://www.acmicpc.net/problem/1761
*/

data class Node(val node: Int, val weight: Int)
data class Tc(val node1: Int, val node2: Int)

private lateinit var graph: Array<ArrayList<Node>>
private lateinit var distance: IntArray // 최상위 노드에서부터 본인 노드까지 거리
private lateinit var visited: BooleanArray // 방문 여부
private lateinit var depth: IntArray // 최상위 노드에서부터 본인 노드까지 깊이
private lateinit var parent: Array<IntArray> // 본인의 부모 노드
private val tcList = ArrayList<Tc>()

private const val MAXNODE = 40000
private val maxLength = log2(MAXNODE.toDouble()).toInt() + 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    graph = Array(n + 1) { arrayListOf() }
    repeat(n - 1) {
        val (a, b, w) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Node(b, w))
        graph[b].add(Node(a, w))
    }

    distance = IntArray(n + 1) { 0 }
    visited = BooleanArray(n + 1) { false }
    depth = IntArray(n + 1) { 0 }
    parent = Array(n + 1) { IntArray(maxLength + 1) }

    val m = br.readLine().toInt()
    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        tcList.add(Tc(a, b))
    }

    br.close()
}

private fun getResult(): ArrayList<Int> {
    dfs(now = 1, d = 0, dis = 0) // 모든 정점에 대해 깊이, 거리, 부모 정보 초기화
    addParent() // 모든 정점에 대해 2의 거듭제곱 부모 추가

    val result = ArrayList<Int>()
    repeat(tcList.size) { i ->
        val node1 = tcList[i].node1
        val node2 = tcList[i].node2

        result.add(distance[node1] + distance[node2] - 2 * distance[lca(node1, node2)])
    }

    return result
}

private fun dfs(now: Int, d: Int, dis: Int) {
    visited[now] = true
    depth[now] = d
    distance[now] = dis

    for (neighbor in graph[now]) {
        if (!visited[neighbor.node]) { // 본인 노드와 이웃한 정점이 방문한 적이 없는 경우
            parent[neighbor.node][0] = now
            dfs(now = neighbor.node, d = d + 1, dis = dis + neighbor.weight)
        }
    }
}

private fun addParent() {
    for (depth in 1 until maxLength + 1) {
        for (i in 1 until graph.size) {
            parent[i][depth] = parent[parent[i][depth - 1]][depth - 1] // 부모의 부모를 추가
        }
    }
}

private fun lca(node1: Int, node2: Int): Int {

    var shallowNode: Int  // 얕은 노드. 최상위 정점까지의 깊이를 비교했을 때 가까운 노드
    var deepNode = if (depth[node1] > depth[node2]) {
        shallowNode = node2
        node1
    } else {
        shallowNode = node1
        node2
    } // 깊은 노드. 최상위 정점까지 깊이를 비교했을 때 더 깊은 노드

    for (i in maxLength + 1 downTo 0) { // 서로 깊이가 같을 때까지 깊은 정점은 부모 정점으로 이동
        if (depth[deepNode] - depth[shallowNode] >= (1 shl i)) {
            deepNode = parent[deepNode][i]
        }
    }

    while (shallowNode != deepNode) { // 두 정점이 같을때까지 서로 부모 정점으로 이동
        shallowNode = parent[shallowNode][0]
        deepNode = parent[deepNode][0]
    }

    return shallowNode
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
