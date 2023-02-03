import java.util.StringTokenizer

/*
* 백준 12851번. 숨바꼭질 2
* https://www.acmicpc.net/problem/12851
*/

data class Position(val pos: Int, val time: Int)

private var start = -1
private var end = -1
private var resultTime = 0
private var resultMethod = 0

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val st = StringTokenizer(br.readLine())
    start = st.nextToken().toInt()
    end = st.nextToken().toInt()

    br.close()
}

private fun getResult() {
    val visited = BooleanArray(200_000 + 1) { false }
    val queue = ArrayDeque<Position>()
    queue.add(Position(start, 0))

    var findFlag = false
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        val pos = current.pos
        val time = current.time
        visited[pos] = true

        if (!findFlag) {
            if (pos == end) {
                resultTime = time
                resultMethod++
                findFlag = true
            }
            else {
                if (pos - 1 >= 0 && !visited[pos - 1]) queue.add(Position(pos - 1, time + 1))
                if (pos + 1 <= 100_000 && !visited[pos + 1]) queue.add(Position(pos + 1, time + 1))
                if (pos * 2 <= 100_000 && !visited[pos * 2] && pos * 2 <= 200_000) queue.add(Position(pos * 2, time + 1))
            }
        } else {
            if (time == resultTime && pos == end) resultMethod++
        }
    }
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("$resultTime\n")
    bw.write("$resultMethod\n")
    bw.flush()
    bw.close()
}
