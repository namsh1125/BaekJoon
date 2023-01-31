import kotlin.math.min

/*
* 백준 2150번. Strongly Connected Component
* https://www.acmicpc.net/problem/2150
*/

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var finished: BooleanArray // SCC가 성립하는지 여부. 성립되면 true
private lateinit var d: IntArray
private var id = 0
private val sccList = arrayListOf<ArrayDeque<Int>>()
private val stack = ArrayDeque<Int>()

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (e, v) = br.readLine().split(' ').map { it.toInt() }

    d = IntArray(size = e + 1) { 0 }

    graph = Array(size = e + 1) { arrayListOf() }
    repeat(v) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        graph[a].add(b)
    }

    finished = BooleanArray(size = e + 1) { false }

    br.close()
}

private fun getResult() {
    for (i in 1 until graph.size) {
        if (d[i] == 0) dfs(i)
    }
}

private fun dfs(i: Int): Int {

    d[i] = ++id // 정점에 고유 id 할당
    stack.add(i) // 스택에 자신을 삽입

    var parent = d[i]
    for (next in graph[i]) {

        // 아직 방문하지 않은 정점이면, 탐색을 진행하고 부모 갱신
        if (d[next] == 0) parent = min(parent, dfs(next))

        // 방문은 했으나 SCC로 성립되지 않은 점이라면
        else if (!finished[next]) parent = min(parent, d[next])
    }

    // 자신과 자식 정점들이 갈 수 있는 가장 높은 정점이 자신일 경우. 즉 부모노드가 자기 자신인 경우
    if (parent == d[i]) {
        val scc = ArrayDeque<Int>()

        // 스택에서 자기 자신을 찾을 때 까지 SCC에 추가
        while (true) {
            val t = stack.removeLast()
            scc.add(t)
            finished[t] = true
            if (t == i) break
        }

        scc.sort() // 출력 조건. SCC 내부 정점들을 오름차순으로 정렬
        sccList.add(scc)
        sccList.sortBy { it[0] } // 출력 조건. 가장 작은 정점의 정점 번호 순으로 정렬
    }

    return parent // 자신의 부모 값 반환
}

private fun printResult() {
    val bw = System.out.bufferedWriter()

    bw.write("${sccList.size}\n")
    for (i in sccList.indices) {
        sccList[i].forEach {
            bw.write("$it ")
        }
        bw.write("-1\n")
    }

    bw.flush()
    bw.close()
}
