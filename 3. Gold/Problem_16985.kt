import kotlin.math.min
import java.util.ArrayDeque

/*
* 백준 16985번. Maaaaaaaaaze
* https://www.acmicpc.net/problem/16985
*/

data class Position(val i: Int, val j: Int, val k: Int)
data class Move(val pos: Position, val time: Int)

private val base = Array(5) { Array(5) { IntArray(5) { EMPTY } } }

private val di = arrayOf(1, -1, 0, 0, 0, 0)
private val dj = arrayOf(0, 0, 1, -1, 0, 0)
private val dk = arrayOf(0, 0, 0, 0, 1, -1)

private const val BLOCK = 0
private const val EMPTY = 1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    for (i in 0 until 5) {
        for (j in 0 until 5) {
            val line = br.readLine().split(' ').map { it.toInt() }
            for (k in 0 until 5) {
                base[i][j][k] = line[k]
            }
        }
    }

    br.close()
}

private fun getBoard(i: Int): Array<IntArray> {
    val board = Array(5) { IntArray(5) { EMPTY } }

    for (j in 0 until 5) {
        for (k in 0 until 5) {
            board[j][k] = base[i][j][k]
        }
    }

    return board
}

private fun getResult(): Int {
    var result = Int.MAX_VALUE
    val newMaze = Array(5) { Array(5) { IntArray(5) { EMPTY } } }

    // 배치 할 순서 정하기
    val arr = intArrayOf(0, 1, 2, 3, 4)
    val visited = BooleanArray(5) { false }
    val order = IntArray(5)

    fun permute(depth: Int) {
        if (depth == 5) {
            var board1 = getBoard(order[0])
            var board2 = getBoard(order[1])
            var board3 = getBoard(order[2])
            var board4 = getBoard(order[3])
            var board5 = getBoard(order[4])

            // 판의 순서를 정했으니, 각 판마다 돌릴 횟수 정하기
            for (a in 0 until 4) {
                newMaze[0] = board1

                for (b in 0 until 4) {
                    newMaze[1] = board2

                    for (c in 0 until 4) {
                        newMaze[2] = board3

                        for (d in 0 until 4) {
                            newMaze[3] = board4

                            for (e in 0 until 5) {
                                newMaze[4] = board5
                                result = min(result, escape(newMaze))
                                board5 = rotateBoard(board5)
                            }
                            board4 = rotateBoard(board4)
                        }
                        board3 = rotateBoard(board3)
                    }
                    board2 = rotateBoard(board2)
                }
                board1 = rotateBoard(board1)
            }
        }

        for (i in arr.indices) {
            if (!visited[i]) {
                visited[i] = true
                order[depth] = arr[i]
                permute(depth + 1)
                visited[i] = false
            }
        }
    }

    permute(0)

    return if (result == Int.MAX_VALUE) -1 else result
}

private fun rotateBoard(board: Array<IntArray>): Array<IntArray> {
    val rotate = Array(5) { IntArray(5) { EMPTY } }

    for (i in 0 until 5) {
        for (j in 0 until 5) {
            rotate[j][5 - i - 1] = board[i][j]
        }
    }

    return rotate
}

private fun escape(maze: Array<Array<IntArray>>): Int {
    var result = Int.MAX_VALUE

    // 무방향 그래프이므로 i = 0 -> i = 4만 확인
    // 입구 (0, 0, 0) -> 출구 (4, 4, 4)
    if (maze[0][0][0] != BLOCK && maze[4][4][4] != BLOCK) {
        result = min(result, escape(maze, Position(0, 0, 0), Position(4, 4, 4)))
    }

    // 꼭짓점 (0, 0, 4) -> 출구 (4, 4, 0)
    if (maze[0][0][4] != BLOCK && maze[4][4][0] != BLOCK) {
        result = min(result, escape(maze, Position(0, 0, 4), Position(4, 4, 0)))
    }

    // 꼭짓점 (0, 4, 0) -> 출구 (4, 0, 4)
    if (maze[0][4][0] != BLOCK && maze[4][0][4] != BLOCK) {
        result = min(result, escape(maze, Position(0, 4, 0), Position(4, 0, 4)))
    }

    // 꼭짓점 (0, 4, 4) -> 출구 (4, 0, 0)
    if (maze[0][4][4] != BLOCK && maze[4][0][0] != BLOCK) {
        result = min(result, escape(maze, Position(0, 4, 4), Position(4, 0, 0)))
    }

    return result
}

private fun escape(maze: Array<Array<IntArray>>, entrance: Position, exit: Position): Int {
    val visited = Array(5) { Array(5) { BooleanArray(5) { false } } }

    val queue = ArrayDeque<Move>()
    queue.add(Move(entrance, 0))
    visited[entrance.i][entrance.j][entrance.k] = true

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        val pos = cur.pos

        for (l in 0 until 6) {
            val ni = pos.i + di[l]
            val nj = pos.j + dj[l]
            val nk = pos.k + dk[l]

            if (isInRange(ni, nj, nk) && !visited[ni][nj][nk] && maze[ni][nj][nk] != BLOCK) {
                if (ni == exit.i && nj == exit.j && nk == exit.k) return cur.time + 1

                visited[ni][nj][nk] = true
                queue.add(Move(Position(ni, nj, nk), cur.time + 1))
            }
        }
    }

    return Int.MAX_VALUE
}

private fun isInRange(i: Int, j: Int, k: Int): Boolean {
    return (i in 0 until 5 && j in 0 until 5 && k in 0 until 5)
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
