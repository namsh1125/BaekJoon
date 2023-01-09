/*
* 백준 2263번. 트리의 순회
* https://www.acmicpc.net/problem/2263
*/

private val result = arrayListOf<Int>()
private lateinit var inOrder: List<Int>
private lateinit var postOrder: List<Int>
private val idx = mutableMapOf<Int, Int>()

fun main() {

    initVariable()
    preOrder(0, inOrder.size - 1, 0, postOrder.size - 1)
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    inOrder = br.readLine().split(' ').map { it.toInt() }
    postOrder = br.readLine().split(' ').map { it.toInt() }

    inOrder.forEachIndexed { index, i ->
        idx[i] = index
    }
}

fun preOrder(inStart: Int, inEnd: Int, postStart: Int, postEnd: Int) {

    if (inStart > inEnd || postStart > postEnd) return

    val root = postOrder[postEnd]
    result.add(root)

    val rootIndex = idx[root]!!
    val subtreeLength = rootIndex - inStart

    preOrder(inStart, rootIndex - 1, postStart, postStart + subtreeLength - 1) // 왼쪽
    preOrder(rootIndex + 1, inEnd, postStart + subtreeLength, postEnd - 1)

}

fun printResult() {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
