/*
* 백준 3109번. 빵집
* https://www.acmicpc.net/problem/3109
*/

private var r = -1
private var c = -1
private lateinit var map: Array<CharArray>

private const val BLANK = '.'
private const val PIPELINE = '-'
private const val BLOCK = 'b'

// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
private val di = arrayOf(-1, 0, 1)

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toInt() }.also {
        r = it[0]
        c = it[1]
    }

    map = Array(r) { CharArray(c) { ' ' } }

    for (i in 0 until r) {
        val line = br.readLine()
        for (j in 0 until c) {
            map[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Int {
    var result = 0

    fun dfs(i: Int, j: Int): Boolean {
        if (j == c - 1) {
            result++
            map[i][j] = PIPELINE
            return true
        }

        for (k in 0 until 3) {
            val ni = i + di[k]

            if (isInRange(ni, j + 1) && map[ni][j + 1] == BLANK) {
                val canGo = dfs(ni, j + 1)

                // 해당 경로로 갈 수 있는 경우 Pipeline 설치
                if (canGo) {
                    map[i][j] = PIPELINE
                    return true
                }

                // 해당 경로로 갈 수 없는 경우 경로를 막아 시간 복잡도 줄이기
                else {
                    map[i][j] = BLOCK
                }
            }
        }

        return false
    }

    for (i in 0 until r) dfs(i, 0)

    return result
}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in 0 until r && j in 0 until c
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
