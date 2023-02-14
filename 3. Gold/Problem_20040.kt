/*
* 백준 20040번. 사이클 게임
* https://www.acmicpc.net/problem/20040
*/

data class Edge(val spot1: Int, val spot2: Int)

private lateinit var parent: IntArray
private val edgeList = ArrayList<Edge>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    parent = IntArray(n) { it }
    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        edgeList.add(Edge(a, b))
    }

    br.close()
}

private fun getResult(): Int {
    var time = 1

    edgeList.forEach { edge ->
        val spot1 = edge.spot1
        val spot2 = edge.spot2

        if (findParent(spot1) != findParent(spot2)) unionParent(spot1, spot2)
        else return time

        time++
    }

    return 0
}

private fun unionParent(i: Int, j: Int) {
    val parent1 = findParent(i)
    val parent2 = findParent(j)

    if (parent1 < parent2) parent[parent2] = parent1
    else parent[parent1] = parent2
}

private fun findParent(i: Int): Int {
    return if (i == parent[i]) i
    else {
        parent[i] = findParent(parent[i])
        parent[i]
    }
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
