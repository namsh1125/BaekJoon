import java.util.PriorityQueue

/*
* 백준 19598번. 최소 회의실 개수
* https://www.acmicpc.net/problem/19598
*/

data class Meeting(
        val start: Int,
        val end: Int
) : Comparable<Meeting> {
    override fun compareTo(other: Meeting): Int {
        return start - other.start
    }
}

private var n = -1
private val meetingList = PriorityQueue<Meeting>()

private const val EMPTY = -1

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    n = br.readLine().toInt()
    repeat(n) {
        val (s, e) = br.readLine().split(' ').map { it.toInt() }
        meetingList.add(Meeting(s, e))
    }

    br.close()
}

private fun getResult(): Int {
    val room = IntArray(n) { EMPTY }

    while (meetingList.isNotEmpty()) {
        val meeting = meetingList.remove()

        for (i in room.indices) {
            if (room[i] == EMPTY || room[i] <= meeting.start) {
                room[i] = meeting.end
                break
            }
        }
    }

    for (i in room.indices) {
        if (room[i] == EMPTY) return i
    }

    return n
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}