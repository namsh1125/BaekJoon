import kotlin.math.min

/*
* 백준 1806번. 부분합
* https://www.acmicpc.net/problem/1806
*/

private lateinit var arr: List<Long>
private var s = Int.MIN_VALUE

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (_, b) = br.readLine().split(' ').map { it.toInt() }

    arr = br.readLine().split(' ').map { it.toLong() }
    s = b
}

private fun getResult(): Int {

    var result = Int.MAX_VALUE
    val prefixSum = getPrefixSum()

    var leftPointer = 0
    var rightPointer = 0

    while (leftPointer < prefixSum.size && rightPointer < prefixSum.size) {

        val sum = prefixSum[rightPointer] - prefixSum[leftPointer]
        if (sum >= s) {
            result = min(result, rightPointer - leftPointer)
        }

        if (sum < s) rightPointer++
        else leftPointer++
    }

    return if (result == Int.MAX_VALUE) {
        0
    } else {
        result
    }
}

private fun getPrefixSum(): ArrayList<Long> {

    val prefixSum = arrayListOf<Long>()
    prefixSum.add(0)

    for (i in arr.indices) {
        prefixSum.add(prefixSum[i] + arr[i])
    }

    return prefixSum
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("${result}\n")
    bw.flush()
    bw.close()
}
