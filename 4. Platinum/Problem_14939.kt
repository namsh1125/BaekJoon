import kotlin.math.min

/*
* 백준 14939번. 불 끄기
* https://www.acmicpc.net/problem/14939
*/

private val di = arrayOf(1, 0, 0, 0, -1)
private val dj = arrayOf(0, -1, 0, 1, 0)

private fun main() {

    val map = initVariable()
    val result = getResult(map)
    printResult(result)
}

private fun initVariable(): Array<BooleanArray> {

    val br = System.`in`.bufferedReader()
    val map = Array(size = 10) { BooleanArray(size = 10) { false } }

    for (i in 0 until 10) {

        val line = br.readLine()
        for (j in 0 until 10) {
            if (line[j] == 'O') map[i][j] = true
        }
    }

    return map
}

private fun getResult(map: Array<BooleanArray>): Int {

    var result = Int.MAX_VALUE
    for (i in 0 until 1024) {

        val copiedMap = copyMap(map)
        var count = 0

        for (j in 0 until 10) {
            if (((1 shl j) and i) != 0) {
                changeState(copiedMap, 0, j)
                count++
            }
        }

        for (k in 0 until 9) {
            for (l in 0 until 10) {
                if (copiedMap[k][l]) {
                    changeState(copiedMap, k + 1, l)
                    count++
                }
            }
        }

        if (lightIsOff(copiedMap)) {
            result = min(result, count)
        }
    }

    return if (result == Int.MAX_VALUE) {
        -1
    } else {
        result
    }
}

private fun copyMap(map: Array<BooleanArray>): Array<BooleanArray> {

    val copiedMap = Array(size = 10) { BooleanArray(size = 10) { false } }

    for (i in map.indices) {
        for (j in map[i].indices) {
            copiedMap[i][j] = map[i][j]
        }
    }

    return copiedMap
}

private fun changeState(copiedMap: Array<BooleanArray>, i: Int, j: Int) {

    for (k in 0 until 5) {

        val ni = i + di[k]
        val nj = j + dj[k]

        if (isInRange(ni, nj)) {
            copiedMap[ni][nj] = !copiedMap[ni][nj]
        }
    }
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0..9 && j in 0..9
}

private fun lightIsOff(copiedMap: Array<BooleanArray>): Boolean {

    for (i in 0 until 10) {
        if (copiedMap[9][i]) return false
    }

    return true
}

private fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
