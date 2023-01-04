/*
* 백준 14226번. 이모티콘
* https://www.acmicpc.net/problem/14226
*/

data class Component(val display: Int, val clipboard: Int, val time: Int)

const val maxSize = 1000

fun main() {

    val s = initVariable()
    val result = getResult(s)
    printResult(result)
}

fun initVariable(): Int {

    val br = System.`in`.bufferedReader()
    return br.readLine().toInt()
}

fun getResult(s: Int): Int {

    val queue = arrayListOf(Component(1, 0, 0))

    val visited = Array(size = maxSize + 1) { BooleanArray(size = maxSize + 1) { false } } // row: display, column: clipboard
    visited[1][0] = true

    while (queue.isNotEmpty()) {

        val current = queue.removeFirst()
        val display = current.display
        val clipboard = current.clipboard
        val time = current.time

        if (display == s) return time
        if (display in 1..maxSize) {

            // 클립보드에 저장
            if (!visited[display][display]) {
                visited[display][display] = true
                queue.add(Component(display, display, time + 1))
            }

            // 화면에 붙여넣기
            if (clipboard in 1..maxSize - display) {
                if (!visited[display + clipboard][clipboard]) {
                    visited[display + clipboard][clipboard] = true
                    queue.add(Component(display + clipboard, clipboard, time + 1))
                }
            }

            // 이모티콘 삭제
            if (!visited[display - 1][clipboard]) {
                visited[display - 1][clipboard] = true
                queue.add(Component(display - 1, clipboard, time + 1))
            }

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
