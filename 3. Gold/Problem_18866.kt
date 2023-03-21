import kotlin.math.max
import kotlin.math.min

/*
* 백준 18866번. 젊은 날의 생이여
* https://www.acmicpc.net/problem/18866
*/

data class Year(var happiness: Int, var fatigue: Int)

private var n = -1
private lateinit var years: Array<Year>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    years = Array(n + 1) { Year(-1, -1) }

    repeat(n) { i ->
        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        years[i + 1] = Year(u, v)
    }

    br.close()
}

private fun getResult(): Int {
    val youth = Array(n + 1) { Year(-1, -1) }
    val old = Array(n + 1) { Year(-1, -1) }

    var minHappy = Int.MAX_VALUE
    var maxFatigue = Int.MIN_VALUE

    for (i in 1..n) {
        if (years[i].happiness != 0) minHappy = min(minHappy, years[i].happiness)
        if (years[i].fatigue != 0) maxFatigue = max(maxFatigue, years[i].fatigue)

        youth[i].happiness = minHappy
        youth[i].fatigue = maxFatigue
    }

    var maxHappy = Int.MIN_VALUE
    var minFatigue = Int.MAX_VALUE

    for (i in n downTo 0) {
        if (years[i].happiness != 0) maxHappy = max(maxHappy, years[i].happiness)
        if (years[i].fatigue != 0) minFatigue = min(minFatigue, years[i].fatigue)

        old[i].happiness = maxHappy
        old[i].fatigue = minFatigue
    }

    var result = -1
    for (i in n - 1 downTo 0) {
        val youthHappy = youth[i].happiness
        val youthFatigue = youth[i].fatigue
        val oldHappy = old[i + 1].happiness
        val oldFatigue = old[i + 1].fatigue

        if (youthHappy > oldHappy && youthFatigue < oldFatigue) {
            result = max(result, i)
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
