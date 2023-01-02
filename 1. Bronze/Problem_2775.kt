/*
* 백준 2775번. 부녀회장이 될테야
* https://www.acmicpc.net/problem/2775
*/

data class Room(val floor: Int, val room: Int)

private val testList = arrayListOf<Room>()
private val resident = Array(size = 15) { IntArray(size = 15) { 0 } }

fun main() {

    initVariable()
    getResident()
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    for (i in 0 until n) {
        testList.add(Room(br.readLine().toInt(), br.readLine().toInt()))
    }
}

fun getResident() {

    for(i in 1 until resident[0].size) { // 호실
        resident[0][i] = i
    }

    for(i in 1 until resident.size) { // 층

        resident[i][1] = resident[i-1][1]
        for(j in 2 until resident[i].size) { // 호실
            resident[i][j] = resident[i][j-1] + resident[i-1][j]
        }
    }
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    testList.forEach {
        bw.write("${resident[it.floor][it.room]}\n")
    }
    bw.flush()
    bw.close()
}
