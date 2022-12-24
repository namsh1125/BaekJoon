import java.util.PriorityQueue
import java.util.Scanner

/*
* 백준 11000번. 강의실 배정
* https://www.acmicpc.net/problem/11000
*/

data class Lecture(val start: Int = 0, val end: Int = 0)

private val lecture = mutableListOf<Lecture>()

fun main() {

    initVariable()
    lecture.sortBy { it.start } // 강의 시작 시각으로 정렬
    println(getResult())

}

fun getResult(): Int {

    val room = PriorityQueue<Int>()

    for (i in 0 until lecture.size) {

        if (i == 0) {
            room.add(lecture[i].end)
            continue
        }

        if (room.peek() <= lecture[i].start) { // 가장 빨리 끝나는 강의에 넣을 수 있다면 (기존 강의실 사용)
            room.poll()
            room.add(lecture[i].end)

        } else { // 새로운 강의실 사용
            room.add(lecture[i].end)
        }

    }

    return room.size
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()

    for (i in 0 until n) {
        lecture.add(Lecture(start = nextInt(), end = nextInt()))
    }
}
