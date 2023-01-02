/*
* 백준 15681번. 트리와 쿼리
* https://www.acmicpc.net/problem/15681
*/

private lateinit var nodeList: Array<ArrayList<Int>>
private lateinit var subTreeCount: IntArray
private val query = ArrayList<Int>()

private var rootNode = -1

fun main() {

    initVariable()
    dfs(-1, rootNode)
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, r, q) = br.readLine().split(' ').map { it.toInt() }

    rootNode = r

    nodeList = Array(size = n + 1) { arrayListOf() }
    for (i in 1 until n) {

        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        nodeList[a].add(b)
        nodeList[b].add(a)
    }

    subTreeCount = IntArray(size = n + 1) { 0 }

    for (j in 0 until q) {
        query.add(br.readLine().toInt())
    }
}

fun dfs(parent: Int, current: Int): Int {

    var count = 0
    for (node in nodeList[current]) {

        if (node == parent) continue
        else count += dfs(current, node)
    }
    subTreeCount[current] = count + 1

    return subTreeCount[current]
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    query.forEach {
        bw.write("${subTreeCount[it]}\n")
    }
    bw.flush()
    bw.close()
}
