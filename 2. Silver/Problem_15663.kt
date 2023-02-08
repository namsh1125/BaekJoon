import java.util.StringTokenizer
import kotlin.text.StringBuilder

/*
* 백준 15663번. N과 M (9)
* https://www.acmicpc.net/problem/15663
*/

private var choose = -1
private val list = ArrayDeque<Int>()
private val sb = StringBuilder()

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    val st = StringTokenizer(br.readLine())
    repeat(n) {
        list.add(st.nextToken().toInt())
    }
    list.sort()

    choose = m

    br.close()
}

private fun getResult() {
    val visited = BooleanArray(list.size) { false }
    val selected = IntArray(choose)

    fun dfs(depth: Int) {

        if (depth == choose) {
            selected.forEach { sb.append("$it ") }
            sb.append("\n")
            return
        }

        var previousNum = -1

        for (i in list.indices) {
            if (!visited[i] && previousNum != list[i]) {
                visited[i] = true
                selected[depth] = list[i]
                previousNum = list[i]
                dfs(depth + 1)
                visited[i] = false
            }
        }
    }

    dfs(0)
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("$sb")
    bw.flush()
    bw.close()
}
