/*
* 백준 16946번. 벽 부수고 이동하기 4
* https://www.acmicpc.net/problem/16946
*/

private lateinit var map: Array<IntArray>

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private const val WALL = 1
private const val BLANK = 0
private const val MOD = 10

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    map = Array(n) { IntArray(m) { -1 } }

    for (i in map.indices) {
        val line = br.readLine()
        for (j in map[i].indices) {
            map[i][j] = line[j] - '0'
        }
    }

    br.close()
}

private fun getResult(): Array<IntArray> {
    val result = Array(map.size) { IntArray(map[0].size) { 0 } }
    val area = Array(map.size) { IntArray(map[0].size) { 0 } }
    val areaNum = Array(map.size) { IntArray(map[0].size) { -1 } }

    separateArea(area, areaNum)

    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == BLANK) {
                result[i][j] = BLANK
                continue
            }

            result[i][j] = 1
            val list = ArrayList<Int>()
            for (k in 0 until 4) {
                val ni = i + di[k]
                val nj = j + dj[k]

                if (isInRange(ni, nj) && map[ni][nj] == BLANK && !list.contains(areaNum[ni][nj])) {
                    result[i][j] += area[ni][nj]
                    list.add(areaNum[ni][nj])
                }
            }
            result[i][j] %= MOD
        }
    }

    return result
}

private fun separateArea(area: Array<IntArray>, areaNum: Array<IntArray>) {
    val areaSize = separate(areaNum)

    // 사이즈 배정
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] != WALL) {
                area[i][j] = areaSize[areaNum[i][j]]
            }
        }
    }
}

private fun separate(areaNum: Array<IntArray>): ArrayList<Int> {
    val visited = Array(map.size) { BooleanArray(map[0].size) { false } }
    var num = 0

    fun dfs(i: Int, j: Int): Int {
        areaNum[i][j] = num
        visited[i][j] = true
        var count = 1

        for (k in 0 until 4) {
            val ni = i + di[k]
            val nj = j + dj[k]

            if (isInRange(ni, nj) && !visited[ni][nj] && map[ni][nj] != WALL) {
                count += dfs(ni, nj)
            }
        }

        return count
    }

    val areaSize = arrayListOf<Int>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == BLANK && !visited[i][j]) {
                areaSize.add(dfs(i, j) % MOD)
                num++
            }
        }
    }

    return areaSize
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in map.indices && j in map[i].indices
}

private fun printResult(result: Array<IntArray>) {
    val bw = System.out.bufferedWriter()

    for (i in result.indices) {
        for (j in result[i].indices) {
            bw.write("${result[i][j]}")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}
