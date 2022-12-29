import java.util.*
import kotlin.math.pow

/*
* 백준 14600번. 샤워실 바닥 깔기 (Small)
* https://www.acmicpc.net/problem/14600
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
        if (floor[i + halfLength - 1][j + halfLength - 1] == 0) {
            floor[i + halfLength - 1][j + halfLength - 1] = count
        }
    }

    if (isNotDrain(i, j + halfLength, halfLength)) {
        if (floor[i + halfLength - 1][j + halfLength] == 0) {
            floor[i + halfLength - 1][j + halfLength] = count
        }
    }

    if (isNotDrain(i + halfLength, j, halfLength)) {
        if (floor[i + halfLength][j + halfLength - 1] == 0) {
            floor[i + halfLength][j + halfLength - 1] = count
        }
    }

    if (isNotDrain(i + halfLength, j + halfLength, halfLength)) {
        if (floor[i + halfLength][j + halfLength] == 0) {
            floor[i + halfLength][j + halfLength] = count
        }
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
            if (floor[a][b] == -1) {
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
