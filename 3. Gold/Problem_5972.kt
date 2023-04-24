import java.util.PriorityQueue

/*
* 백준 5972번. 택배 배송
* https://www.acmicpc.net/problem/5972
*/

data class Node(
    val node: Int,
    val stover: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return stover - other.stover
    }
}

private var n = -1 // 헛간. 노드
private var m = -1 // 길. 엣지
private lateinit var graph: Array<ArrayList<Node>>

private const val INF = 50_000 * 1_000 + 1

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

    graph = Array(n + 1) { arrayListOf() }
    repeat(m) {
        val (a, b, w) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(Node(b, w))
        graph[b].add(Node(a, w))
    }

    br.close()
}

private fun getResult(): Int {
    val minStove = IntArray(n + 1) { INF }
    minStove[1] = 0

    val queue = PriorityQueue<Node>()
    queue.add(Node(1, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.remove()

        for (neighbor in graph[cur.node]) {
            if (minStove[neighbor.node] > cur.stover + neighbor.stover) {
                minStove[neighbor.node] = cur.stover + neighbor.stover
                queue.add(Node(neighbor.node, minStove[neighbor.node]))
            }
        }
    }

    return minStove[n]
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
