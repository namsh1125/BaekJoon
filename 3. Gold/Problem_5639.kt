/*
* 백준 5639번. 이진 검색 트리
* https://www.acmicpc.net/problem/5639
*/

private val tree = ArrayDeque<Int>()
private val result = arrayListOf<Int>()

private fun main() {

    initVariable()
    postOrder(0, tree.size - 1)
    printResult()
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()

    while (true) {
        val num = br.readLine() ?: break
        tree.add(num.toInt())
    }

    br.close()
}

private fun postOrder(start: Int, end: Int) {

    if (start > end) return

    var root = start + 1
    while (root <= end && tree[root] < tree[start]) {
        root++
    }

    postOrder(start + 1, root - 1)
    postOrder(root, end)
    result.add(tree[start])
}

private fun printResult() {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
