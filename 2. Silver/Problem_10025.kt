import kotlin.math.max

/*
* 백준 10025번. 게으른 백곰
* https://www.acmicpc.net/problem/10025
*/

private var cage = IntArray(size = 1_000_000 + 1) { 0 }
private var reachDistance = 0

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(' ').map { it.toInt() }

    for (i in 0 until n) {
        val (g, x) = br.readLine().split(' ').map { it.toInt() }
        cage[x] = g
    }

    reachDistance = k
}

fun getResult(): Int {

    var result = Int.MIN_VALUE

    var sum = 0
    var window = 2 * reachDistance + 1
    for (i in cage.indices) {

        if (i >= window) sum -= cage[i - window]

        sum += cage[i]
        result = max(result, sum)
    }

    return result
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
