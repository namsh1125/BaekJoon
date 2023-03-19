import java.util.*
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder

/*
* 백준 14725번. 개미굴
* https://www.acmicpc.net/problem/14725
*/

data class Node(val key: String, var next: ArrayList<Node>)

private val root = Node("", arrayListOf())

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    repeat(n) {
        val st = StringTokenizer(br.readLine())
        val k = st.nextToken().toInt()

        var cur = root
        repeat(k) {
            val key = st.nextToken()
            var exist = false

            for (node in cur.next) {
                if (node.key == key) {
                    cur = node
                    exist = true
                    break
                }
            }

            if (!exist) {
                cur.next.add(Node(key, arrayListOf()))
                cur = cur.next[cur.next.lastIndex]
            }
        }
    }

    br.close()
}

private fun getResult(): String {
    val sb = StringBuilder()

    fun dfs(cur: ArrayList<Node>, depth: Int) {
        cur.sortBy { it.key }

        for (node in cur) {
            repeat(depth) { sb.append("--") }
            sb.append("${node.key}\n")

            dfs(node.next, depth + 1)
        }
    }

    dfs(root.next, 0)

    return sb.toString()
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
