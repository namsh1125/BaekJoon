import kotlin.math.abs

/*
* 백준 2412번. 암벽 등반
* https://www.acmicpc.net/problem/2412
*/

data class Crack(
    val x: Int,
    val y: Int
) : Comparable<Crack> {
    override fun compareTo(other: Crack): Int = compareValuesBy(this, other, { it.y }, { it.x })
}

data class Position(val index: Int, val time: Int)

private var n = -1 // 홈의 개수
private val crackList = ArrayList<Crack>()

private var t = -1 // 정상 높이

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        t = it[1]
    }

    repeat(n) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        crackList.add(Crack(a, b))
    }

    br.close()
}

private fun getResult(): Int {
    crackList.add(Crack(0, 0))
    crackList.sort()

    val visited = BooleanArray(crackList.size) { false }

    val queue = ArrayDeque<Position>()
    queue.add(Position(0, 0))
    visited[0] = true

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        val index = cur.index
        val currentCrack = crackList[index]

        if (isPeak(currentCrack)) return cur.time

        for (i in index until crackList.size) {
            if (outOfRange(currentCrack, crackList[i])) break

            if (!visited[i] && canGo(currentCrack, crackList[i])) {
                visited[i] = true
                queue.add(Position(i, cur.time + 1))
            }
        }

        for (i in index downTo 0) {
            if (outOfRange(currentCrack, crackList[i])) break

            if (!visited[i] && canGo(currentCrack, crackList[i])) {
                visited[i] = true
                queue.add(Position(i, cur.time + 1))
            }
        }
    }

    return -1
}

private fun canGo(cur: Crack, next: Crack): Boolean {
    return abs(cur.x - next.x) <= 2 && abs(cur.y - next.y) <= 2
}

private fun outOfRange(cur: Crack, next: Crack): Boolean {
    return abs(cur.x - next.x) > 2 && abs(cur.y - next.y) > 2
}

private fun isPeak(crack: Crack): Boolean {
    return crack.y == t
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
