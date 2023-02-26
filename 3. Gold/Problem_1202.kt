import java.util.PriorityQueue

/*
* 백준 1202번. 보석 도둑
* https://www.acmicpc.net/problem/1202
*/

data class Jewel(
    val weight: Int,
    val value: Int
) : Comparable<Jewel> {
    override fun compareTo(other: Jewel): Int {
        if (weight == other.weight) {
            return other.value - value
        }
        return weight - other.weight
    }
}

private lateinit var jewelList: Array<Jewel>
private lateinit var bag: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(' ').map { it.toInt() }

    jewelList = Array(n) { Jewel(0, 0) }
    repeat(n) { i ->
        val (m, v) = br.readLine().split(' ').map { it.toInt() }
        jewelList[i] = Jewel(m, v)
    }

    bag = IntArray(k) { 0 }
    repeat(k) { i ->
        bag[i] = br.readLine().toInt()
    }

    br.close()
}

private fun getResult(): Long {
    jewelList.sort()
    bag.sort()

    val queue = PriorityQueue<Int>(Comparator.reverseOrder())
    var result = 0L
    var j = 0

    for (i in bag.indices) {
        while (j < jewelList.size && jewelList[j].weight <= bag[i]) {
            queue.add(jewelList[j++].value)
        }

        if (queue.isNotEmpty()) {
            result += queue.poll()
        }
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
