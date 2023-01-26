import java.util.StringTokenizer

/*
* 백준 1167번. 트리의 지름
* https://www.acmicpc.net/problem/1167
*/

data class Node(val neighbor: Int, val weight: Int)

private lateinit var graph: Array<ArrayDeque<Node>>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    graph = Array(size = n + 1) { ArrayDeque() }
    repeat(n) {
        val st = StringTokenizer(br.readLine())
        val node1 = st.nextToken().toInt()

        do {
            val node2 = st.nextToken().toInt()
            if (node2 != -1) {
                val weight = st.nextToken().toInt()
                graph[node1].add(Node(node2, weight))
            } else break

        } while (st.hasMoreTokens())
    }
}

private fun getResult(): Int {

    var treeWeight = 0
    var deepNode = 0

    fun dfs(current: Int = 1, parent: Int = 0, weight: Int = 0) {
        if (treeWeight < weight) {
            treeWeight = weight
            deepNode = current
        }

        for (node in graph[current]) {
            if (node.neighbor != parent) {
                dfs(node.neighbor, current, weight + node.weight)
            }
        }
    }

    dfs()
    dfs(current = deepNode)

    return treeWeight
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
