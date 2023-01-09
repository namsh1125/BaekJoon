import java.util.PriorityQueue

/*
* 백준 13549번. 숨바꼭질 3
* https://www.acmicpc.net/problem/13549
*/

data class Position(
    val position: Int,
    val time: Int
) : Comparable<Position> {
    override fun compareTo(other: Position) = time - other.time
}

private const val MAX = 100_000
private var start = -1
private var end = -1

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(' ').map { it.toInt() }

    start = n
    end = k
}

fun getResult(): Int {

    val queue = PriorityQueue<Position>()
    queue.add(Position(start, 0))

    val visited = BooleanArray(size = MAX + 1) { false }
    visited[start] = true
    while (queue.isNotEmpty()) {

        val top = queue.remove()
        visited[top.position] = true

        if (top.position == end) return top.time

        if (top.position - 1 >= 0 && !visited[top.position - 1]) {
            queue.add(Position(top.position - 1, top.time + 1))
        }

        if (top.position + 1 <= MAX && !visited[top.position + 1]) {
            queue.add(Position(top.position + 1, top.time + 1))
        }

        if (top.position * 2 <= MAX && !visited[top.position * 2]) {
            queue.add(Position(top.position * 2, top.time))
        }
    }

    return -1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
