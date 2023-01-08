/*
* 백준 16928번. 뱀과 사다리 게임
* https://www.acmicpc.net/problem/16928
*/

data class Player(val position: Int, val time: Int)
data class Teleport(val from: Int, val to: Int)

private val teleportList = ArrayList<Teleport>()

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    repeat(n + m) {
        val (x, y) = br.readLine().split(' ').map { it.toInt() }
        teleportList.add(Teleport(x, y))
    }
}

fun getResult(): Int {

    val visited = BooleanArray(size = 100 + 1) { false }
    val queue = ArrayDeque<Player>()

    visited[1] = true
    queue.add(Player(1, 0))

    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()

        for (i in 1..6) {

            var next = top.position + i

            if(next == 100) return top.time + 1
            else if(next < 100)  {

                teleportList.forEach {
                    if(it.from == next) next = it.to
                }

                if(!visited[next]) {
                    queue.add(Player(next, top.time + 1))
                    visited[next] = true
                }
            }
            else break
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
