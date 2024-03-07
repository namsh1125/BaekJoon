import kotlin.math.roundToInt

/*
* 백준 18110번. solved.ac
* https://www.acmicpc.net/problem/18110
*/

private var opinion = mutableListOf<Int>()

private fun main() {
    initVariables()
    val result = getAverage(opinion)
    printResult(result)
}

private fun initVariables() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    repeat(n) {
        opinion.add(br.readLine().toInt())
    }

    br.close()
}

private fun getAverage(opinion: MutableList<Int>): Int {
    // 의견이 없는 경우
    if (opinion.isEmpty()) return 0

    // 의견이 있는 경우
    opinion.sort()

    // 제외되는 사람의 수 (위, 아래 각각)
    val removeCount = (opinion.size * 0.15).roundToInt()

    // 제외되는 사람을 제외한 나머지 사람들의 평균
    return opinion.subList(removeCount, opinion.size - removeCount).average().roundToInt()
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write(result.toString())
    bw.flush()
    bw.close()
}
