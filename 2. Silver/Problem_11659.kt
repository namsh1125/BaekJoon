import java.util.StringTokenizer

/*
* 백준 11659번. 구간 합 구하기 4
* https://www.acmicpc.net/problem/11659
*/

data class Testcase(val start: Int, val end: Int)

private val testCaseList = arrayListOf<Testcase>()
private lateinit var num: IntArray
private lateinit var prefixSum: IntArray

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (a, b) = br.readLine().split(' ').map { it.toInt() }

    num = IntArray(size = a) { 0 }
    prefixSum = IntArray(size = a + 1) { 0 }

    val st = StringTokenizer(br.readLine())
    for (i in 0 until a) {
        num[i] = st.nextToken().toInt()
        prefixSum[i + 1] = prefixSum[i] + num[i]
    }

    repeat(b) {
        val (i, j) = br.readLine().split(' ').map { it.toInt() }
        testCaseList.add(Testcase(i, j))
    }

}

fun getResult(): ArrayList<Int> {

    val resultList = arrayListOf<Int>()

    repeat(testCaseList.size) {
        resultList.add(getResult(it))
    }

    return resultList
}

fun getResult(i: Int): Int {

    val start = testCaseList[i].start
    val end = testCaseList[i].end

    return prefixSum[end] - prefixSum[start - 1]
}

fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
