/*
* 백준 16564번. 히오스 프로게이머
* https://www.acmicpc.net/problem/16564
*/

private var available = 0L
private val team = arrayListOf<Int>()

fun main() {

    initVariable()
    team.sort()
    val result = findMaxLevel()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine()!!.split(' ')
    available = k.toLong()

    for (i in 0 until n.toInt()) {
        team.add(br.readLine()!!.toInt())
    }
}

fun findMaxLevel(): Long {

    var start: Long = team[0].toLong()
    var end: Long = team[0] + available

    while (start <= end) {

        val mid = (start + end) / 2
        var needLevel = 0L

        for (i in team.indices) {
            if (team[i] < mid) needLevel += mid - team[i]
        }

        if (needLevel <= available) start = mid + 1
        else end = mid - 1
    }

    return end
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
