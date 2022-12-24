import java.util.Scanner
import kotlin.math.max
import kotlin.math.pow

/*
* 백준 20058번. 마법사 상어와 파이어스톰
* https://www.acmicpc.net/problem/20058
*/

data class Position(val i: Int, val j: Int)

private lateinit var ice: Array<Array<Int>>
private lateinit var visited: Array<Array<Boolean>>
private lateinit var cast: Array<Int>
private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)

fun main() {

    initVariable()

    for (i in cast.indices) {
        fireStorm(i)
    }

    println("${getRemainSum()}")
    println("${getAreaSize()}")
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, q) = nextLine().split(' ').map { it.toInt() }

    val size = 2.0.pow(n.toDouble()).toInt()
    ice = Array(size) { Array(size) { 0 } }
    visited = Array(size) { Array(size) { false } }
    cast = Array(size = q) { 0 }

    for (i in ice.indices) {
        for (j in ice.indices) {
            ice[i][j] = nextInt()
        }
    }

    for (i in 0 until q) {
        cast[i] = nextInt()
    }

}

fun fireStorm(i: Int) {
    rotate(length = 2.0.pow(cast[i]).toInt())
    melt()
}

fun rotate(length: Int) {

    val copyIce = copyIce()

    for (i in ice.indices step length) {
        for (j in ice.indices step length) {
            rotate(i, j, length, copyIce)
        }
    }
}

fun rotate(i: Int, j: Int, length: Int, copyIce: Array<Array<Int>>) {

    for (a in 0 until length) {
        for (b in 0 until length) {
            ice[i + b][j + length - 1 - a] = copyIce[i + a][j + b]
        }
    }
}

fun copyIce(): Array<Array<Int>> {

    val copy = Array(size = ice.size) { Array(size = ice.size) { 0 } }

    for (i in ice.indices) {
        for (j in ice[i].indices) {
            copy[i][j] = ice[i][j]
        }
    }

    return copy
}

fun melt() {

    val meltList = ArrayList<Position>()

    for (i in ice.indices) {
        for (j in ice.indices) {
            var count = 0 // 인접한 얼음의 갯수

            for (k in 0 until 4) {
                val ni = i + dx[k]
                val nj = j + dy[k]

                if (!isInRange(i = ni, j = nj)) {
                    continue
                }

                if (ice[ni][nj] > 0) {
                    count++
                }
            }

            if (count < 3) {
                meltList.add(Position(i, j))
            }
        }
    }

    for (k in meltList.indices) {
        if (ice[meltList[k].i][meltList[k].j] > 0) {
            ice[meltList[k].i][meltList[k].j] -= 1
        }
    }

}

fun isInRange(i: Int, j: Int): Boolean {
    return (i >= 0 && i < ice.size && j >= 0 && j < ice[0].size)
}

fun getRemainSum(): Int {

    var result = 0

    for (i in ice.indices) {
        for (j in ice.indices) {
            result += ice[i][j]
        }
    }

    return result

}

fun getAreaSize(): Int {

    var result = 0

    for (i in ice.indices) {
        for (j in ice.indices) {
            if (!visited[i][j] && ice[i][j] != 0) {
                result = max(result, dfs(i, j))
            }
        }
    }

    return result
}

fun dfs(i: Int, j: Int): Int {

    var result = 1
    visited[i][j] = true

    for (k in 0 until 4) {

        val ni = i + dx[k]
        val nj = j + dy[k]

        if (isInRange(ni, nj) && !visited[ni][nj] && ice[ni][nj] > 0) {
            result += dfs(ni, nj)
        }

    }

    return result
}
