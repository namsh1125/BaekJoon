import java.util.*
import kotlin.math.pow

/*
* 백준 14601번. 샤워실 바닥 깔기 (Large)
* https://www.acmicpc.net/problem/14601
*/

private lateinit var floor: Array<Array<Int>>
private var count = 0

fun main() {

    initVariable()
    tiling(0, 0, floor.size)
    printResult()
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val length = 2.0.pow(nextLine().toInt()).toInt()
    floor = Array(size = length) { Array(size = length) { 0 } }

    val (x, y) = nextLine().split(' ').map { it.toInt() }
    floor[length - y][x - 1] = -1
}

fun tiling(i: Int, j: Int, length: Int) {

    count++
    val halfLength = length / 2

    if (isNotDrain(i, j, halfLength)) {
        floor[i + halfLength - 1][j + halfLength - 1] = count
    }

    if (isNotDrain(i, j + halfLength, halfLength)) {
        floor[i + halfLength - 1][j + halfLength] = count
    }

    if (isNotDrain(i + halfLength, j, halfLength)) {
        floor[i + halfLength][j + halfLength - 1] = count
    }

    if (isNotDrain(i + halfLength, j + halfLength, halfLength)) {
        floor[i + halfLength][j + halfLength] = count
    }

    if (length == 2) return

    tiling(i, j, halfLength)
    tiling(i, j + halfLength, halfLength)
    tiling(i + halfLength, j, halfLength)
    tiling(i + halfLength, j + halfLength, halfLength)
}

fun isNotDrain(i: Int, j: Int, length: Int): Boolean {

    for (a in i until i + length) {
        for (b in j until j + length) {
            if (floor[a][b] != 0) {
                return false
            }
        }
    }
    return true
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    for (i in floor.indices) {
        for (j in floor.indices) {
            bw.write("${floor[i][j]} ")
        }
        bw.write("\n")
    }
    bw.close()
}
