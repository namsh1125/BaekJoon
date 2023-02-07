import java.awt.Point
import kotlin.math.min

/*
* 백준 2261번. 가장 가까운 두 점
* https://www.acmicpc.net/problem/2261
*/

private val xComp = Comparator<Point> { p1, p2 -> p1.x - p2.x }
private val yComp = Comparator<Point> { p1, p2 -> p1.y - p2.y }

private val pointList = ArrayList<Point>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    repeat(n) {
        val (x, y) = br.readLine().split(' ').map { it.toInt() }
        pointList.add(Point(x, y))
    }

    br.close()
}

private fun getResult(): Int {
    pointList.sortWith(xComp)
    return closest(0, pointList.lastIndex)
}

private fun closest(start: Int, end: Int): Int {

    // 원소가 3개 이하면 일일이 비교해 거리 반환
    if (end - start + 1 < 4) return bruteForce(start, end)

    // 가운데 index
    val mid = (start + end) / 2

    // 왼쪽과 오른쪽 부분에 존재하는 최소 거리
    val minDistance = min(closest(start, mid), closest(mid + 1, end))

    // 중간 영역의 최소 거리
    val band = middleBand(start, mid, end, minDistance)

    // 영역 내 최소 거리 반환
    return min(minDistance, band)
}

private fun bruteForce(start: Int, end: Int): Int {
    var minDistance = Int.MAX_VALUE

    for (i in start until end) {
        for (j in i + 1..end) {
            minDistance = min(minDistance, distance(pointList[i], pointList[j]))
        }
    }

    return minDistance
}

private fun distance(p1: Point, p2: Point): Int {
    return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)
}

private fun middleBand(start: Int, mid: Int, end: Int, distance: Int): Int {
    val list = ArrayList<Point>() // 중간 영역에 있는 점을 저장할 list

    val midX = pointList[mid].x // 구분선
    for (i in start..end) {

        // 점과 구분선 사이의 거리가 최소 거리보다 작다면 list에 추가
        if ((pointList[i].x - midX) * (pointList[i].x - midX) < distance) list.add(pointList[i])
    }

    list.sortWith(yComp) // y좌표를 기준으로 정렬

    var minDistance = distance
    for (i in 0 until list.size - 1) {
        for (j in i + 1 until list.size) {
            val point1 = list[i]
            val point2 = list[j]

            // y좌표의 차이가 minDistance보다 작다면 거리를 비교해 작은 값으로 갱신
            if ((point1.y - point2.y) * (point1.y - point2.y) < minDistance) {
                minDistance = min(minDistance, distance(point1, point2))
            } else {
                break // y좌표가 오름차순으로 졍렬되어 있으므로 minDistance보다 크면 반목분 탈출
            }
        }
    }

    return minDistance
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
