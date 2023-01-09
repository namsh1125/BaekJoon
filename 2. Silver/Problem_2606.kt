/*
* 백준 2606번. 바이러스
* https://www.acmicpc.net/problem/2606
*/

private lateinit var network: Array<ArrayList<Int>>
private lateinit var visited: BooleanArray

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    network = Array(size = n + 1) { arrayListOf() }
    visited = BooleanArray(size = n + 1) { false }

    repeat(m) {

        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        network[u].add(v)
        network[v].add(u)
    }
}

fun getResult(): Int {

    val queue = ArrayDeque<Int>()
    queue.add(1)

    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()
        for (i in network[top]) {
            if (!visited[i]) {
                queue.add(i)
                visited[i] = true
            }
        }
    }

    var result = 0
    visited.forEach {
        if (it) result++
    }

    return result - 1 // 1번 컴퓨터 제외
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
