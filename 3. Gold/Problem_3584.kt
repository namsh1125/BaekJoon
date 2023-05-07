import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log2

/*
* 백준 3584번. 가장 가까운 공통 조상
* https://www.acmicpc.net/problem/3584
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val tc = br.readLine().toInt()
    repeat(tc) {
        val node = br.readLine().toInt()
        val graph = Array(node + 1) { ArrayList<Int>() }

        repeat(node - 1) {
            val (a, b) = br.readLine().split(' ').map { it.toInt() }
            graph[a].add(b)
        }

        val (node1, node2) = br.readLine().split(' ').map { it.toInt() }
        bw.write("${getResult(graph, node1, node2)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun getResult(graph: Array<ArrayList<Int>>, node1: Int, node2: Int): Int {
    val depth = IntArray(graph.size) { -1 }
    val parent = Array(graph.size) { IntArray(log2(graph.size.toDouble()).toInt() + 1) { -1 } }

    val root = findRootNode(graph)
    makeTree(graph, root, depth, parent)

    return findLca(depth, parent, node1, node2)
}

private fun findRootNode(graph: Array<ArrayList<Int>>): Int {
    val root = BooleanArray(graph.size) { true }

    for (i in 1 until graph.size) {
        for (node in graph[i]) {
            root[node] = false
        }
    }

    for (i in 1 until graph.size) {
        if (root[i]) return i
    }

    return -1
}

private fun makeTree(graph: Array<ArrayList<Int>>, root: Int, depth: IntArray, parent: Array<IntArray>) {
    val queue: Queue<Int> = LinkedList()
    queue.add(root)
    depth[root] = 0

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

    updateParent(parent, depth)
}

private fun updateParent(parent: Array<IntArray>, depth: IntArray) {
    for (d in 1 until parent[0].size) {
        for (i in 1 until parent.size) {
            if (parent[i][d - 1] != -1) {
                parent[i][d] = parent[parent[i][d - 1]][d - 1]
            }
        }
    }
}

private fun findLca(depth: IntArray, parent: Array<IntArray>, node1: Int, node2: Int): Int {
    var shallowNode: Int
    var deepNode: Int

    if (depth[node1] < depth[node2]) {
        deepNode = node2
        shallowNode = node1
    } else {
        deepNode = node1
        shallowNode = node2
    }

    // 서로 깊이 맞추기
    val maxDepth = parent[0].size
    for (d in maxDepth - 1 downTo 0) {
        if (depth[deepNode] - depth[shallowNode] >= (1 shl d)) {
            deepNode = parent[deepNode][d]
        }
    }

    // 같을 때까지 위로 올리기
    while (shallowNode != deepNode) {
        for (d in maxDepth - 1 downTo 0) {
            if (parent[shallowNode][d] != parent[deepNode][d]) {
                shallowNode = parent[shallowNode][d]
                deepNode = parent[deepNode][d]
            }
        }

        shallowNode = parent[shallowNode][0]
        deepNode = parent[deepNode][0]
    }

    return shallowNode
}
