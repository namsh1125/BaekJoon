/*
* 백준 21736번. 헌내기는 친구가 필요해
* https://www.acmicpc.net/problem/21736
*/

data class Point(val i: Int, val j: Int)

private var n = -1
private var m = -1
private lateinit var campus: Array<ArrayList<Char>>
private lateinit var current: Point // 현재 위치

private val di = listOf(-1, 1, 0, 0)
private val dj = listOf(0, 0, -1, 1)

private fun main() {
    initVariables()
    val result = getResult()
    printResult(result)
}

private fun initVariables() {
    val br = System.`in`.bufferedReader()

    // n, m 입력
    br.readLine().split(" ").map { it.toInt() }.also {
        n = it[0]
        m = it[1]
    }

    // n x m 크기의 캠퍼스 생성
    campus = Array(n) { ArrayList() }

    // 캠퍼스 정보 입력 및 도연이의 현재 위치 저장
    for (i in 0 until n) {
        val line = br.readLine()

        for (j in 0 until m) {
            campus[i].add(line[j])

            if (line[j] == 'I') {
                current = Point(i, j)
            }
        }
    }

    br.close()
}

private fun getResult(): String {
    val queue = ArrayDeque<Point>()
    val visited = Array(n) { BooleanArray(m) }
    var result = 0

    queue.add(current)
    while (queue.isNotEmpty()) {
        val pos = queue.removeFirst()

        for (k in 0 until 4) {
            val ni = pos.i + di[k]
            val nj = pos.j + dj[k]

            // 캠퍼스 범위 내에 있고 빈 공간이며 방문하지 않았는지 확인
            if (ni < 0 || ni >= n || nj < 0 || nj >= m) continue
            if (campus[ni][nj] == 'X' || visited[ni][nj]) continue

            // 도연이가 만날 수 있는 친구라면
            if (campus[ni][nj] == 'P') {
                result++
            }

            // 방문 처리 후 큐에 추가
            visited[ni][nj] = true
            queue.add(Point(ni, nj))
        }
    }

    return if (result == 0) "TT" else result.toString()
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result.toString())
    bw.flush()
    bw.close()
}
