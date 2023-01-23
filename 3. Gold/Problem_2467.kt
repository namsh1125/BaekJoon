import kotlin.math.abs

/*
* 백준 2467번. 용액
* https://www.acmicpc.net/problem/2467
*/

data class Result(val solution1: Int, val solution2: Int, val sum: Int)

private fun main() {
    val solutionList = initVariable()
    val result = getResult(solutionList)
    printResult(result)
}

private fun initVariable(): List<Int> {
    val br = System.`in`.bufferedReader()
    br.readLine()
    return br.readLine().split(' ').map { it.toInt() }
}

private fun getResult(solutionList: List<Int>): Result {

    var leftPointer = 0
    var rightPointer = solutionList.size - 1
    var result = Result(solutionList[leftPointer], solutionList[rightPointer], solutionList[0] + solutionList[rightPointer])

    while (leftPointer < rightPointer) {

        val sum = solutionList[leftPointer] + solutionList[rightPointer]
        if (abs(result.sum) > abs(sum)) {
            result = Result(solutionList[leftPointer], solutionList[rightPointer], sum)
        }

        if (sum < 0) leftPointer++
        else rightPointer--
    }

    return result
}

private fun printResult(result: Result) {
    val bw = System.out.bufferedWriter()
    bw.write("${result.solution1} ${result.solution2}\n")
    bw.flush()
    bw.close()
}
