/*
* 백준 16566번. 카드 게임
* https://www.acmicpc.net/problem/16566
*/

private var n = 0
private val blueCardList = ArrayDeque<Int>()
private val redOrder = ArrayDeque<Int>()

private lateinit var parent: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (_, m, _) = br.readLine().split(' ').map { it.toInt() }
    parent = IntArray(m + 1) { it }

    br.readLine().split(' ').forEach { card ->
        blueCardList.add(card.toInt())
    }

    br.readLine().split(' ').forEach { card ->
        redOrder.add(card.toInt())
    }
}

private fun getResult(): ArrayDeque<Int> {
    val result = ArrayDeque<Int>()
    blueCardList.sort()

    redOrder.forEach { red ->
        var start = 0
        var end = blueCardList.size

        while (start < end) {
            val mid = (start + end) / 2
            var blue = blueCardList[mid]

            if (blue <= red) start = mid + 1
            else end = mid
        }

        var index = findParent(end)
        result.add(blueCardList[index])
        unionParent(index, index + 1)
    }

    return result
}

private fun findParent(i: Int): Int {
    return if (i == parent[i]) i
    else {
        parent[i] = findParent(parent[i])
        parent[i]
    }
}

private fun unionParent(i: Int, j: Int) {
    val parentI = findParent(i)
    val parentJ = findParent(j)

    if (parentI < parentJ) parent[parentI] = parentJ
    else parent[parentJ] = parentI
}

private fun printResult(result: ArrayDeque<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
