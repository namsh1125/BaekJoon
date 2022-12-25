import java.util.LinkedList
import java.util.Scanner

/*
* 백준 12761번. 돌다리
* https://www.acmicpc.net/problem/12761
*/


fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (a, b, start, end) = nextLine().split(' ').map { it.toInt() }
    val bridge = Array(size = 100001) { 0 } // 최소 방문 횟수
    val visit = Array(size = 100001) { false }
    val dx = arrayOf(1, -1, a, -a, b, -b, a, b)

    // BFS
    val queue = LinkedList<Int>()
    queue.add(start)

    while (!queue.isEmpty()) {

        val x = queue[0]
        queue.poll()

        for (i in 0 until 8) {

            val nx = if (i < 6) {
                x + dx[i]
            } else {
                x * dx[i]
            }

            if (nx in 0..100000 && !visit[nx]) {
                queue.add(nx)
                visit[nx] = true
                bridge[nx] = bridge[x] + 1
            }
        }
    }

    println(bridge[end])

}
