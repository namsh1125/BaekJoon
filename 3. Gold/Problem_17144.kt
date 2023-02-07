/*
* 백준 17144번. 미세먼지 안녕!
* https://www.acmicpc.net/problem/17144
*/

data class Position(val i: Int, val j: Int)

private const val AIRCLEANER = -1
private val airCleanerPosition = ArrayDeque<Position>()
private lateinit var map: Array<IntArray>
private var time = -1

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (r, c, t) = br.readLine().split(' ').map { it.toInt() }

    map = Array(r) { IntArray(c) { 0 } }
    for (i in map.indices) {
        val line = br.readLine().split(' ').map { it.toInt() }
        for (j in map[i].indices) {
            map[i][j] = line[j]

            if (map[i][j] == AIRCLEANER) {
                airCleanerPosition.add(Position(i, j))
            }
        }
    }

    time = t

    br.close()
}

private fun getResult(): Int {
    while (time-- > 0) {
        diffusion()
        activateAirCleaner()
    }

    return countDust()
}

private fun diffusion() {
    val add = Array(map.size) { IntArray(map.first().size) { 0 } } // 확산된 양을 저장하는 배열
    val sub = Array(map.size) { IntArray(map.first().size) { 0 } } // 확산되어 사라진 양을 저장하는 배열

    for (i in map.indices) {
        for (j in map[i].indices) {
            for (k in 0 until 4) {
                val ni = i + di[k]
                val nj = j + dj[k]

                if (isInRange(ni, nj) && map[ni][nj] != AIRCLEANER) {
                    add[ni][nj] += map[i][j] / 5
                    sub[i][j] += map[i][j] / 5
                }
            }
        }
    }

    for (i in map.indices) {
        for (j in map[i].indices) {
            map[i][j] = map[i][j] + add[i][j] - sub[i][j]
        }
    }
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun activateAirCleaner() {
    val up = airCleanerPosition[0] // 공기 청정기의 윗부분
    val down = airCleanerPosition[1] // 공기 청정기의 아래부분

    val r = map.size
    val c = map[0].size

    // =========================
    for (j in up.j - 1 downTo 1) {
        map[up.i][j] = map[up.i][j - 1]
        map[down.i][j] = map[down.i][j - 1]
    }

    // =========================
    for (i in up.i downTo 1) {
        if (map[i][0] == AIRCLEANER) continue
        map[i][0] = map[i - 1][0]
    }

    for (i in down.i until r - 1) {
        if (map[i][0] == AIRCLEANER) continue
        map[i][0] = map[i + 1][0]
    }

    // =========================
    for (j in 0 until c - 1) {
        map[0][j] = map[0][j + 1]
        map[r - 1][j] = map[r - 1][j + 1]
    }

    // =========================
    for (i in 0 until up.i) {
        map[i][c - 1] = map[i + 1][c - 1]
    }

    for (i in r - 1 downTo down.i + 1) {
        map[i][c - 1] = map[i - 1][c - 1]
    }

    // =========================
    for (j in c - 1 downTo up.j + 2) {
        map[up.i][j] = map[up.i][j - 1]
        map[down.i][j] = map[down.i][j - 1]
    }

    // =========================
    map[up.i][up.j + 1] = 0
    map[down.i][down.j + 1] = 0
}

private fun countDust(): Int {
    var result = 0

    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] != AIRCLEANER) result += map[i][j]
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
