import java.lang.Math.sqrt

/*
* 백준 1929번. 소수 구하기
* https://www.acmicpc.net/problem/1929
*/

private lateinit var isPrime: BooleanArray

private var m = -1
private var n = -1

fun main() {

    initVariable()
    getPrimeNumber()
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (num1, num2) = br.readLine().split(' ').map { it.toInt() }

    isPrime = BooleanArray(size = num2 + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    m = num1
    n = num2
}

fun getPrimeNumber() {

    for (i in 2 until sqrt(n.toDouble()).toInt() + 1) {

        if (isPrime[i]) {
            var j = 2
            while (i * j <= n) {
                isPrime[i * j] = false
                j++
            }
        }
    }
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    for (i in m..n) {
        if (isPrime[i]) bw.write("$i\n")
    }
    bw.flush()
    bw.close()
}
