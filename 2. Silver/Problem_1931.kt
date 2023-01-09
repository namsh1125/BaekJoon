/*
* 백준 1931번. 회의실 배정
* https://www.acmicpc.net/problem/1931
*/

data class Meeting(val start: Long, val end: Long)

fun main() {

    val meetingList = initVariable()
    val result = getResult(meetingList)
    printResult(result)
}

fun initVariable(): ArrayList<Meeting> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val meetingList = arrayListOf<Meeting>()
    repeat(n) {
        val (start, end) = br.readLine().split(' ').map { it.toLong() }
        meetingList.add(Meeting(start, end))
    }

    return meetingList
}

fun getResult(meetingList: ArrayList<Meeting>): Long {

    meetingList.sortWith(compareBy({ it.end }, { it.start }))

    var count = 0L
    var previousEndTime = 0L
    meetingList.forEach {
        if (previousEndTime <= it.start) {
            previousEndTime = it.end
            count++
        }
    }

    return count
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
