/*
* 백준 12896번. 스크루지 민호
* https://www.acmicpc.net/problem/12896
*/

private var n = -1
private lateinit var graph: Array<ArrayList<Int>>

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
        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    br.close()
}

private fun getResult(): Int {
    val diameter = getTreeDiameter()
    return if (diameter % 2 == 0) diameter / 2 else diameter / 2 + 1
}

private fun getTreeDiameter(): Int {
    val node1: Int
    val node2: Int

    fun findFurthestNode(node: Int): Int {
        var result = Pair(node, 0)
        val visited = BooleanArray(n + 1) { false }

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(node, 0))

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            if (result.second < cur.second) result = cur // 거리가 더 먼 경우 해당 노드로 변경

            for (neighbor in graph[cur.first]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    queue.add(Pair(neighbor, cur.second + 1))
                }
            }
        }

        return result.first
    }

    node1 = findFurthestNode(1) // 임의의 노드에서 가장 먼 노드 찾기
    node2 = findFurthestNode(node1)

    return distance(node1, node2)
}

private fun distance(start: Int, destination: Int): Int {
    val visited = BooleanArray(n + 1) { false }
    var result = 0

    fun dfs(cur: Int, dist: Int) {
        if (cur == destination) {
            result = dist
        }

        for (neighbor in graph[cur]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true
                dfs(neighbor, dist + 1)
            }
        }
    }

    dfs(start, 0)

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
