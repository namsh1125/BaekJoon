import kotlin.math.max
import kotlin.math.min

/*
* 백준 2162번. 선분 그룹
* https://www.acmicpc.net/problem/2162
*/

data class Point(val x: Int, val y: Int)
data class Line(val point1: Point, val point2: Point)
data class Result(val groupCount: Int, val lineCount: Int)

private val lineList = ArrayDeque<Line>()
private lateinit var parent: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    parent = IntArray(size = n) { it }

    repeat(n) {
        val (x1, y1, x2, y2) = br.readLine().split(' ').map { it.toInt() }
        lineList.add(Line(Point(x1, y1), Point(x2, y2)))
    }
}

private fun getResult(): Result {

    for (i in lineList.indices) {
        for (j in i + 1 until lineList.size) {
            if (isConnected(i, j)) {
                unionParent(i, j)
            }
        }
    }

    return Result(getGroupCount(), getLineCount().max())
}

private fun isConnected(i: Int, j: Int): Boolean {

    val point1 = lineList[i].point1
    val point2 = lineList[i].point2
    val point3 = lineList[j].point1
    val point4 = lineList[j].point2

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

    return if (op > 0) 1
    else if (op == 0) 0
    else -1
}

private fun unionParent(i: Int, j: Int) {
    val parent1 = getParent(i)
    val parent2 = getParent(j)

    if (parent1 < parent2) parent[parent2] = parent1
    else if (parent1 > parent2) parent[parent1] = parent2
}

private fun getParent(i: Int): Int {
    return if (parent[i] == i) i
    else getParent(parent[i])
}

private fun getGroupCount(): Int {

    var groupCount = 0

    for (i in parent.indices) {
        if (parent[i] == i) groupCount++
    }

    return groupCount
}

private fun getLineCount(): IntArray {

    val lineCount = IntArray(size = parent.size) { 0 }

    for (i in parent.indices) {
        lineCount[getParent(i)]++
    }

    return lineCount
}

private fun printResult(result: Result) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.groupCount}\n")
    bw.write("${result.lineCount}\n")
    bw.flush()
    bw.close()
}
