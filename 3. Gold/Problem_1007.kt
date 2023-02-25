import java.awt.Point
import kotlin.math.min
import kotlin.math.sqrt

/*
* 백준 1007번. 벡터 매칭
* https://www.acmicpc.net/problem/1007
*/

private fun main() {
    val result = ArrayList<Double>()
    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()

    repeat(tc) {
        val n = br.readLine().toInt()
        val pointList = arrayListOf<Point>()

        repeat(n) {
            val (x, y) = br.readLine().split(' ').map { it.toInt() }
            pointList.add(Point(x, y))
        }

        result.add(getResult(pointList))
    }
    br.close()

    printResult(result)
}

private fun getResult(pointList: ArrayList<Point>): Double {
    var result = Double.MAX_VALUE

    var xSum = 0
    var ySum = 0
    pointList.forEach { point ->
        xSum += point.x
        ySum += point.y
    }

    val selected = ArrayDeque<Point>()
    fun dfs(index: Int) {
        if (selected.size == pointList.size / 2) {
            var x1Sum = 0
            var y1Sum = 0

            selected.forEach { point ->
                x1Sum += point.x
                y1Sum += point.y
            }

            val x2Sum = xSum - x1Sum
            val y2Sum = ySum - y1Sum
            val size = sqrt((x1Sum - x2Sum).toDouble() * (x1Sum - x2Sum) + (y1Sum - y2Sum).toDouble() * (y1Sum - y2Sum))

            result = min(result, size)
            return
        }

        if (index == pointList.size) return

        selected.add(pointList[index])
        dfs(index + 1)

        selected.removeLast()
        dfs(index + 1)
    }

    dfs(0)

    return result
}

private fun printResult(result: ArrayList<Double>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
