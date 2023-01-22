import kotlin.math.abs

/*
* 백준 2166번. 다각형의 면적
* https://www.acmicpc.net/problem/2166
*/

data class Position(val x: Long, val y: Long)

private fun main() {
    val positionList = initVariable()
    val result = getResult(positionList)
    printResult(result)
}

private fun initVariable(): ArrayDeque<Position> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val positionList = ArrayDeque<Position>()
    repeat(n) {
        val (x, y) = br.readLine().split(' ').map { it.toLong() }
        positionList.add(Position(x, y))
    }

    return positionList
}

private fun getResult(positionList: ArrayDeque<Position>): Double {

    var area = 0.0
    val standardPoint = positionList[0]

    for (i in 1 until positionList.size - 1) {
        area += getArea(standardPoint, positionList[i], positionList[i + 1])
    }

    return abs(area)
}

private fun getArea(point1: Position, point2: Position, point3: Position): Double {
    return ((point1.x * point2.y + point2.x * point3.y + point3.x * point1.y) - (point1.y * point2.x + point2.y * point3.x + point3.y * point1.x)) / 2.0
}

private fun printResult(result: Double) {
    val bw = System.out.bufferedWriter()
    bw.write(String.format("%.1f", result))
    bw.flush()
    bw.close()
}
