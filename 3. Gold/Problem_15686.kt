import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

/*
* 백준 15686번. 치킨 배달
* https://www.acmicpc.net/problem/15686
*/

data class Position(val i: Int, val j: Int)

private lateinit var map: Array<IntArray>
private val houseList = ArrayList<Position>()
private val chickenHouseList = ArrayList<Position>()
private var maxChickenHouse = -1

private const val INF = 101

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    val map = Array(n) { IntArray(n) { -1 } }
    for (i in map.indices) {
        val st = StringTokenizer(br.readLine())

        for (j in map[i].indices) {
            map[i][j] = st.nextToken().toInt()

            if (map[i][j] == 1) houseList.add(Position(i, j))
            if (map[i][j] == 2) chickenHouseList.add(Position(i, j))
        }
    }

    maxChickenHouse = m

    br.close()
}

private fun getResult(): Int {

    var result = Int.MAX_VALUE
    val remain = BooleanArray(chickenHouseList.size) { false }

    fun dfs(index: Int, selected: Int) {
        if (selected == maxChickenHouse || index == chickenHouseList.size) { // 남아있는 치킨집이 정해진 경우
            var cityChickenDistance = 0

            // 도시의 치킨 거리를 구해준다
            houseList.forEachIndexed { _, house ->
                var chickenDistance = INF

                chickenHouseList.forEachIndexed { index, chickenHouse ->
                    if (remain[index]) {
                        chickenDistance = min(chickenDistance, distance(house, chickenHouse))
                    }
                }

                cityChickenDistance += chickenDistance
            }

            result = min(result, cityChickenDistance)
            return
        }

        // 남아있는 경우
        remain[index] = true
        dfs(index + 1, selected + 1)

        // 폐업
        remain[index] = false
        dfs(index + 1, selected)
    }

    dfs(0, 0)
    return result
}

private fun distance(p1: Position, p2: Position): Int {
    return abs(p1.i - p2.i) + abs(p1.j - p2.j)
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
