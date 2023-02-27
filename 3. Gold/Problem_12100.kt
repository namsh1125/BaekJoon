import kotlin.math.max

/*
* 백준 12100번. 2048 (Easy)
* https://www.acmicpc.net/problem/12100
*/

private lateinit var board: Array<LongArray>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    board = Array(n) { LongArray(n) { 0 } }
    for (i in board.indices) {
        val line = br.readLine().split(' ').map { it.toLong() }
        for (j in board[i].indices) {
            board[i][j] = line[j]
        }
    }

    br.close()
}

private fun getResult(): Long {
    var result = 0L

    fun move(board: Array<LongArray>, time: Int) {
        if (time == 5) {
            for (i in board.indices) {
                for (j in board[i].indices) {
                    result = max(result, board[i][j])
                }
            }

            return
        }

        move(up(copyBoard(board)), time + 1)
        move(down(copyBoard(board)), time + 1)
        move(left(copyBoard(board)), time + 1)
        move(right(copyBoard(board)), time + 1)
    }

    move(board, 0)

    return result
}

private fun copyBoard(board: Array<LongArray>): Array<LongArray> {
    val n = board.size
    val newBoard = Array(n) { LongArray(n) { 0 } }

    for (i in 0 until n) {
        for (j in 0 until n) {
            newBoard[i][j] = board[i][j]
        }
    }

    return newBoard
}

private fun up(board: Array<LongArray>): Array<LongArray> {
    var queue = ArrayDeque<Long>()
    val n = board.size

    for (j in 0 until n) {
        for (i in 0 until n) {
            if (board[i][j] != 0L) {
                queue.add(board[i][j])
                board[i][j] = 0
            }
        }

        queue = combine(queue)

        var i = 0
        while (queue.isNotEmpty()) {
            board[i][j] = queue.removeFirst()
            i++
        }
    }

    return board
}

private fun down(board: Array<LongArray>): Array<LongArray> {
    var queue = ArrayDeque<Long>()
    val n = board.size

    for (j in 0 until n) {
        for (i in n - 1 downTo 0) {
            if (board[i][j] != 0L) {
                queue.add(board[i][j])
                board[i][j] = 0
            }
        }

        queue = combine(queue)

        var i = n - 1
        while (queue.isNotEmpty()) {
            board[i][j] = queue.removeFirst()
            i--
        }
    }

    return board
}

private fun left(board: Array<LongArray>): Array<LongArray> {
    var queue = ArrayDeque<Long>()
    val n = board.size

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] != 0L) {
                queue.add(board[i][j])
                board[i][j] = 0
            }
        }

        queue = combine(queue)

        var j = 0
        while (queue.isNotEmpty()) {
            board[i][j] = queue.removeFirst()
            j++
        }
    }

    return board
}

private fun right(board: Array<LongArray>): Array<LongArray> {
    var queue = ArrayDeque<Long>()
    val n = board.size

    for (i in 0 until n) {
        for (j in n - 1 downTo 0) {
            if (board[i][j] != 0L) {
                queue.add(board[i][j])
                board[i][j] = 0
            }
        }

        queue = combine(queue)

        var j = n - 1
        while (queue.isNotEmpty()) {
            board[i][j] = queue.removeFirst()
            j--
        }
    }

    return board
}

private fun combine(queue: ArrayDeque<Long>): ArrayDeque<Long> {
    val result = ArrayDeque<Long>()

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        if (queue.isNotEmpty() && cur == queue.first()) {
            queue.removeFirst()
            result.add(cur * 2)
        } else {
            result.add(cur)
        }
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
