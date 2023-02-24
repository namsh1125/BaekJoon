import kotlin.math.abs

/*
* 백준 2473번. 세 용액
* https://www.acmicpc.net/problem/2473
*/

private lateinit var solution: ArrayList<Long>

private const val MAX = 1_000_000_000L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    solution = br.readLine().split(' ').map { it.toLong() } as ArrayList
}

private fun getResult(): ArrayList<Long> {
    solution.sort()

    var result = arrayListOf(MAX, MAX, MAX)
    var resultSum = result.sum()

    for (i in 0..solution.lastIndex - 2) {
        var start = i + 1
        var end = solution.lastIndex

        while (start < end) {
            val sum = solution[i] + solution[start] + solution[end]
            if (abs(sum) < abs(resultSum)) {
                result = arrayListOf(solution[i], solution[start], solution[end])
                resultSum = sum
            }

            if (sum < 0) start++
            else end--
        }
    }

    return result
}

private fun printResult(result: ArrayList<Long>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
