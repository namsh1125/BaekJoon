import java.util.*

/*
* 백준 11651번. 좌표 정렬하기 2
* https://www.acmicpc.net/problem/11651
*/

data class Position(val x: Int, val y: Int)

private val positionList = arrayListOf<Position>()

fun main() {

    initVariable()
    positionList.sortWith(compareBy({ it.y }, { it.x }))

    val bw = System.out.bufferedWriter()
    positionList.forEach {
        bw.write("${it.x} ${it.y}\n")
    }
    bw.flush()
    bw.close()
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()
    for (i in 0 until n) {
        positionList.add(Position(nextInt(), nextInt()))
    }

}
