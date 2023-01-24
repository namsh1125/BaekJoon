import kotlin.math.max
import kotlin.math.min

/*
* 백준 17387번. 선분 교차 2
* https://www.acmicpc.net/problem/17387
*/

data class Point(val x: Long, val y: Long)
data class Line(val point1: Point, val point2: Point)

private val line = Array(size = 2) { Line(Point(0L, 0L), Point(0L, 0L)) }

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()

    repeat(2) { i ->
        val (x1, y1, x2, y2) = br.readLine().split(' ').map { it.toLong() }
        line[i] = Line(Point(x1, y1), Point(x2, y2))
    }
}

private fun getResult(): Int {
    return if (isConnected()) 1 else 0
}

private fun isConnected(): Boolean {

    val point1 = line[0].point1
    val point2 = line[0].point2
    val point3 = line[1].point1
    val point4 = line[1].point2

    val ccwBaseLine1 = ccw(point1, point2, point3) * ccw(point1, point2, point4)
    val ccwBaseLine2 = ccw(point3, point4, point1) * ccw(point3, point4, point2)

    if (ccwBaseLine1 == 0 && ccwBaseLine2 == 0) {
        return if (max(point1.x, point2.x) < min(point3.x, point4.x)) false
        else if (min(point1.x, point2.x) > max(point3.x, point4.x)) false
        else if (max(point1.y, point2.y) < min(point3.y, point4.y)) false
        else if (min(point1.y, point2.y) > max(point3.y, point4.y)) false
        else true
    }

    return ccwBaseLine1 <= 0 && ccwBaseLine2 <= 0
}

private fun ccw(point1: Point, point2: Point, point3: Point): Int {
    val op = (point1.x * point2.y + point2.x * point3.y + point3.x * point1.y) - (point2.x * point1.y + point3.x * point2.y + point1.x * point3.y)

    return if (op > 0L) 1
    else if (op == 0L) 0
    else -1
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("${result}\n")
    bw.flush()
    bw.close()
}
