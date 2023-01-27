/*
* 백준 1865번. 웜홀
* https://www.acmicpc.net/problem/1865
*/

data class Node(val neighbor: Int, val time: Int)

private lateinit var graph: Array<ArrayDeque<Node>>
private const val INF = 999_999_999

private fun main() {

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val tc = br.readLine().toInt()

    repeat(tc) {
        val (n, m, w) = br.readLine().split(' ').map { it.toInt() }
        val graph = Array(size = n + 1) { ArrayDeque<Node>() }

        repeat(m) {
            val (s, e, t) = br.readLine().split(' ').map { it.toInt() }
            graph[s].add(Node(e, t))
            graph[e].add(Node(s, t))
        }

        repeat(w) {
            val (s, e, t) = br.readLine().split(' ').map { it.toInt() }
            graph[s].add(Node(e, -t))
        }

        if (getResult(graph)) bw.write("YES\n")
        else bw.write("NO\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun getResult(graph: Array<ArrayDeque<Node>>): Boolean {

    val time = IntArray(size = graph.size) { INF }

    time[1] = 0
    for (i in 1 until graph.size) { // (최대) node -1 번의 개수만큼 초기화
        for (j in 1 until graph.size) {
            for (node in graph[j]) {
                if (time[node.neighbor] > time[j] + node.time) {
                    time[node.neighbor] = time[j] + node.time
                }
            }
        }
    }

    var hasCycle = false
    for (i in 1 until graph.size) {
        for (node in graph[i]) {
            if (time[node.neighbor] > time[i] + node.time) { // 초기화 이후 업데이트가 발생한다면 음의 사이클 존재
                hasCycle = true
            }
        }
    }

    return hasCycle
}
