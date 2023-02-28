/*
* 백준 10775번. 공항
* https://www.acmicpc.net/problem/10775
*/

private lateinit var parent: IntArray
private val docking = ArrayList<Int>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val gateNum = br.readLine().toInt()
    parent = IntArray(gateNum + 1) { it }

    val p = br.readLine().toInt()
    repeat(p) {
        val g = br.readLine().toInt()
        docking.add(g)
    }

    br.close()
}

private fun getResult(): Int {
    var result = 0

    for (i in docking.indices) {
        val gate = findParent(docking[i])

        if (gate != 0) {
            parent[gate] = findParent(gate - 1)
            result++
        } else {
            break
        }
    }

    return result
}

private fun findParent(i: Int): Int {
    return if (parent[i] == i) i
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
