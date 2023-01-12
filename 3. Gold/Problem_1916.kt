import kotlin.math.min
import java.util.PriorityQueue
import java.util.StringTokenizer

/*
* 백준 1916번. 최소비용 구하기
* https://www.acmicpc.net/problem/1916
*/

data class Bus(
    val destiny: Int,
    val cost: Int
) : Comparable<Bus> {
    override fun compareTo(other: Bus): Int {
        return cost - other.cost
    }
}

private lateinit var graph: Array<ArrayList<Bus>>
private var start = -1
private var end = -1
private const val INF = 999_999_999

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    graph = Array(size = n + 1) { ArrayList() }
    repeat(m) {
        val (start, end, cost) = br.readLine().split(' ').map { it.toInt() }
        graph[start].add(Bus(end, cost))
    }

    val st = StringTokenizer(br.readLine())
    start = st.nextToken().toInt()
    end = st.nextToken().toInt()
}

fun getResult(): Int {

    val visited = BooleanArray(size = graph.size) { false }
    val cost = IntArray(size = graph.size) { INF } // 시작점에서 다른 도시까지 가는데 최소 비용
    val queue = PriorityQueue<Bus>()

    cost[start] = 0
    queue.add(Bus(start, 0))

    while (queue.isNotEmpty()) {

        val now = queue.remove().destiny

        if (!visited[now]) {
            visited[now] = true

            graph[now].forEach {
                val destiny = it.destiny
                cost[destiny] = min(cost[destiny], cost[now] + it.cost)
                queue.add(Bus(destiny, cost[destiny]))
            }
        }
    }

    return cost[end]
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
