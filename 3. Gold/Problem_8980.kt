import java.lang.Integer.min
import java.util.Scanner

/*
* 백준 8980번. 택배
* https://www.acmicpc.net/problem/8980
*/

data class Info(val start: Int, val end: Int, val box: Int)

private val deliver = arrayListOf<Info>()
private var limit = 0
private var end = 0

fun main() {

    initVariable()
    println(getResult())
}

fun getResult(): Int {

    sortDeliverInfo()

    var result = 0
    val available = Array(size = end) { limit } // 실을 수 있는 박스 개수

    for (i in deliver.indices) {

        // 배달지점까지 박스를 얼마나 더 넣을 수 있는지 확인
        var maxCount = limit
        for (j in deliver[i].start until deliver[i].end) {
            maxCount = min(maxCount, available[j])
        }

        // 현재 위치에서 실을 수 있는 박스 개수 구하기
        val availableBox = min(deliver[i].box, maxCount)

        // 배달지까지 현재 실린 박스 개수 업데이트
        for (j in deliver[i].start until deliver[i].end) {
            available[j] -= availableBox
        }

        // 트럭에 실은 박스 개수 업데이트
        result += availableBox
    }

    return result
}

fun sortDeliverInfo() {
    deliver.sortWith(compareBy({ it.end }, { it.start }))
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    end = nextInt()
    limit = nextInt()

    val m = nextInt()
    for (i in 0 until m) {
        deliver.add(Info(nextInt(), nextInt(), nextInt()))
    }

}
