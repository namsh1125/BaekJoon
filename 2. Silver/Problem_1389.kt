import kotlin.math.min

/*
* 백준 1389번. 케빈 베이컨의 6단계 법칙
* https://www.acmicpc.net/problem/1389
*/

private lateinit var graph: Array<IntArray>
private const val INF = 6

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    graph = Array(size = n) { IntArray(size = n) { INF } }

    for (i in graph.indices) {
        graph[i][i] = 0
    }

    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() - 1 }
        graph[a][b] = 1
        graph[b][a] = 1
    }

    br.close()
}

private fun getResult(): Int {
    floydWarshall()
    val kevinBacon = getkevinBacon()
    return getResult(kevinBacon)
}

private fun floydWarshall() {
    for (k in graph.indices) {
        for (i in graph.indices) {
            for (j in graph.indices) {
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
            }
        }
    }
}

private fun getkevinBacon(): ArrayList<Int> {

    val kevinBacon = arrayListOf<Int>()
    for (i in graph.indices) {
        var relation = 0
        for (j in graph[i].indices) {
            relation += graph[i][j]
        }
        kevinBacon.add(relation)
    }

    return kevinBacon
}

private fun getResult(kevinBacon: ArrayList<Int>): Int {
    var result = 0

    for (i in 1 until kevinBacon.size) {
        if (kevinBacon[result] > kevinBacon[i]) result = i
    }

    return result + 1 // 배열은 0부터 시작하기 때문
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
