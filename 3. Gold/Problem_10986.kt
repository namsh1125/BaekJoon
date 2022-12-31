import java.util.*

/*
* 백준 10986번. 나머지 합
* https://www.acmicpc.net/problem/10986
*/

private lateinit var count: Array<Long>

fun main() {

    initVariable()
    val result = getReult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    count = Array(size = m) { 0L }
    val numList = StringTokenizer(br.readLine())

    val prefixSum = Array(size = n + 1) { 0 }
    for (i in 1..n) {

        val num = numList.nextToken().toInt()
        prefixSum[i] = (prefixSum[i - 1] + num) % m
        count[prefixSum[i]]++
    }

}

fun getReult(): Long {

    var result = count[0]

    for (i in count.indices) {
        result += count[i] * (count[i] - 1) / 2 // nC2
    }

    return result
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
