import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log2

/*
* 백준 11438번. LCA 2
* https://www.acmicpc.net/problem/11438
*/

data class TC(val node1: Int, val node2: Int)

private var n = -1 // 노드의 개수
private var maxDepth = -1 // 트리의 최대 깊이
private lateinit var graph: Array<ArrayList<Int>>
private lateinit var depth: IntArray // 최상의 노드로부터 어느 만큼 떨어져 있는지 담고 있는 배열
private lateinit var parent: Array<IntArray> // i번째 노드의 2^j번째 부모를 담고 있는 배열

private var m = -1 // 테스트 케이스 개수
private lateinit var TcList: List<TC>

private const val NONE = 0

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    n = br.readLine().toInt()
    maxDepth = log2(n.toDouble()).toInt() + 1

    graph = Array(n + 1) { arrayListOf() }
    depth = IntArray(n + 1) { -1 }
    parent = Array(n + 1) { IntArray(maxDepth) { NONE } }

    repeat(n - 1) {
        val (node1, node2) = br.readLine().split(' ').map { it.toInt() }
        graph[node1].add(node2)
        graph[node2].add(node1)
    }

    m = br.readLine().toInt()
    TcList = List(m) {
        val (node1, node2) = br.readLine().split(' ').map { it.toInt() }
        TC(node1, node2)
    }

    br.close()
}

private fun getResult(): String {
    makeTree()

    return buildString {
        for (tc in TcList) {
            appendLine(lca(tc.node1, tc.node2))
        }
    }
}

private fun makeTree() {
    val queue: Queue<Int> = LinkedList()
    queue.offer(1)
    depth[1] = 0

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (neighbor in graph[cur]) {
            if (depth[neighbor] == -1) {
                depth[neighbor] = depth[cur] + 1
                parent[neighbor][0] = cur
                queue.offer(neighbor)
            }
        }
    }

    updateParent()
}

private fun updateParent() {
    for (depth in 1 until maxDepth) { // 2^0 즉 1번째 부모는 이미 세팅되어 있어 제외
        for (index in 2..n) { // 노드 n개에 대해 (1번 노드는 최상위 노드라 제외)
            if (parent[index][depth - 1] != NONE) {
                parent[index][depth] = parent[parent[index][depth - 1]][depth - 1]
            }
        }
    }
}

private fun lca(node1: Int, node2: Int): Int {
    var shallowNode: Int // 얕은 노드. 최상위 노드로부터 더 가까운 노드
    var deepNode: Int // 깊은 노드. 최상위 노드로부터 더 먼 노드

    if (depth[node1] > depth[node2]) {
        shallowNode = node2
        deepNode = node1
    } else {
        shallowNode = node1
        deepNode = node2
    }

    // 서로 깊이가 같을 때까지 깊은 노드를 위로 이동 (얕은 노드의 깊이까지 깊은 노드를 위로 이동)
    for (i in maxDepth downTo 0) {
        if (depth[deepNode] - depth[shallowNode] >= (1 shl i)) {
            deepNode = parent[deepNode][i]
        }
    }

    // 두 노드가 같아질 때까지 부모 노드로 이동 (최소 공통 조상일 때까지 위로 이동)
    while (shallowNode != deepNode) {
        for (i in maxDepth - 1 downTo 0) {
            if (parent[shallowNode][i] != parent[deepNode][i]) {
                shallowNode = parent[shallowNode][i]
                deepNode = parent[deepNode][i]
            }
        }

        shallowNode = parent[shallowNode][0]
        deepNode = parent[deepNode][0]
    }

    return shallowNode
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}