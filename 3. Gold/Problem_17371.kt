import java.awt.Point

/*
* 백준 17371번. 이사
* https://www.acmicpc.net/problem/17371
*/

private var n = -1
private val pointList = ArrayList<Point>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    repeat(n) {
        val (x, y) = br.readLine().split(' ').map { it.toInt() }
        pointList.add(Point(x, y))
    }

    br.close()
}

private fun getResult(): Point {
    var minIndex = 0
    var minDist = Int.MAX_VALUE

    for (i in 0 until n) {
        var maxIndex = i
        var maxDist = -1

        for (j in 0 until n) {
            if (i == j) continue
            else {
                val point1 = pointList[i]
                val point2 = pointList[j]

                val dist = distance(point1.x, point1.y, point2.x, point2.y)
                if (maxDist < dist) {
                    maxDist = dist
                    maxIndex = i
                }
            }
        }

        if (minDist > maxDist) {
            minDist = maxDist
            minIndex = maxIndex
        }
    }

    return pointList[minIndex]
}

private fun distance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)
}

private fun printResult(result: Point) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.x} ${result.y}\n")
    bw.flush()
    bw.close()
}
