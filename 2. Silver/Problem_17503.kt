import java.util.PriorityQueue

/*
* 백준 17503번. 맥주 축제
* https://www.acmicpc.net/problem/17503
*/

data class Beer(
    val preference: Int,
    val level: Int
) : Comparable<Beer> {
    override fun compareTo(other: Beer): Int = preference - other.preference
}

private val beerList = ArrayDeque<Beer>()
private var day = -1
private var preferenceSum = -1L

fun main() {

    initVariable()
    sortBeerList()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m, k) = br.readLine().split(' ')

    day = n.toInt()
    preferenceSum = m.toLong()

    for (i in 0 until k.toInt()) {
        val (v, c) = br.readLine().split(' ').map { it.toInt() }
        beerList.add(Beer(v, c))
    }
}

fun sortBeerList() {
    beerList.sortBy { it.level }
}

fun getResult(): Int {

    var sum = 0
    val priorityQueue = PriorityQueue<Beer>()

    for (beer in beerList) {

        priorityQueue.add(beer)
        sum += beer.preference

        if (priorityQueue.size > day) {
            val removeBeer = priorityQueue.poll()
            sum -= removeBeer.preference
        }

        if(priorityQueue.size == day && sum >= preferenceSum) {
            return beer.level
        }
    }

    return -1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
