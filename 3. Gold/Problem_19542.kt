import java.lang.Integer.max

/*
* 백준 19542번. 전단지 돌리기
* https://www.acmicpc.net/problem/19542
*/

private lateinit var tree: Array<ArrayList<Int>>
private lateinit var dp: IntArray
private lateinit var visited: BooleanArray
private var position = -1
private var power = -1

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, s, d) = br.readLine().split(' ').map { it.toInt() }

    tree = Array(size = n + 1) { arrayListOf() }
    for (i in 2..n) {

        val (x, y) = br.readLine().split(' ').map { it.toInt() }
        tree[x].add(y)
        tree[y].add(x)
    }

    dp = IntArray(size = tree.size) { 0 }
    visited = BooleanArray(size = tree.size) { false }

    position = s
    power = d
}

fun getResult(): Int {

    visited[position] = true
    dfs(position)

    var result = 0
    for (i in 1 until tree.size) {
        if (i != position && dp[i] >= power) result++
    }

    return result * 2 // 왕복
}

fun dfs(node: Int): Int {

    var distance = 0
    for (i in tree[node].indices) {

        val next = tree[node][i]
        if (!visited[next]) {
            visited[next] = true
            distance = max(distance, dfs(next))
        }
    }
    dp[node] = distance

    return dp[node] + 1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
