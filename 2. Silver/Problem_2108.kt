import kotlin.math.roundToInt

/*
* 백준 2108번. 통계학
* https://www.acmicpc.net/problem/2108
*/

private val num = arrayListOf<Int>()

fun main() {

    initVariable()
    sortNumber()
    val result = arrayListOf(getAverage(), getMedian(), getMode(), getRange())
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    for (i in 0 until n) {
        num.add(br.readLine().toInt())
    }
}

fun sortNumber() {
    num.sort()
}

fun getAverage(): Int {
    return num.average().roundToInt()
}

fun getMedian(): Int {
    return num[num.size / 2]
}

fun getMode(): Int {

    val map = num.groupingBy { it }.eachCount()
    val maxValue = map.maxOf { it.value }
    val maxMap = map.filter { it.value == maxValue }
    val mapKeys = maxMap.keys.sorted()

    return if (mapKeys.size == 1) {
        mapKeys.first()
    } else {
        mapKeys[1]
    }
}

fun getRange(): Int {
    return num[num.size - 1] - num[0]
}

fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
