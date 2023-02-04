import java.util.StringTokenizer
import kotlin.text.StringBuilder

/*
* 백준 15650번. N과 M (2)
* https://www.acmicpc.net/problem/15650
*/

private lateinit var selected: BooleanArray
private var m = -1
private val sb = StringBuilder()

private fun main() {
    initVariable()
    getResult(0, 0)
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    selected = BooleanArray(n) { false }

    m = st.nextToken().toInt()

    br.close()
}

private fun getResult(index: Int, count: Int) {

    if (count == m) {
        for (i in selected.indices) {
            if (selected[i]) sb.append("${i + 1} ")
        }

        sb.append("\n")
        return
    }

    if (index == selected.size) return

    selected[index] = true
    getResult(index + 1, count + 1)

    selected[index] = false
    getResult(index + 1, count)
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("$sb")
    bw.flush()
    bw.close()
}
