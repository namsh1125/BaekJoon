import kotlin.text.StringBuilder

/*
* 백준 15666번. N과 M (12)
* https://www.acmicpc.net/problem/15666
*/

private lateinit var arr: List<Int>
private val selected = ArrayDeque<Int>()
private var length = -1
private val sb = StringBuilder()

private fun main() {
    initVariable()
    getResult(0, 0)
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    arr = br.readLine().split(' ').map { it.toInt() }
    arr = arr.sorted().distinct()

    length = m

    br.close()
}

private fun getResult(index: Int, count: Int) {

    if (count == length) {
        selected.forEach {
            sb.append("$it ")
        }

        sb.append("\n")
        return
    }

    if (index == arr.size) return

    selected.add(arr[index])
    getResult(index, count + 1)

    selected.removeLast()
    getResult(index + 1, count)
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("$sb")
    bw.flush()
    bw.close()
}
