import java.util.*

/*
* 백준 11650번. 좌표 정렬하기
* https://www.acmicpc.net/problem/11650
*/

data class Position(val x: Int, val y: Int)

private val positionList = arrayListOf<Position>()

fun main() {

    initVariable()
    positionList.sortWith(compareBy({ it.x }, { it.y }))
    positionList.forEach {
        println("${it.x} ${it.y}")
    }
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()
    for (i in 0 until n) {
        positionList.add(Position(nextInt(), nextInt()))
    }

}
