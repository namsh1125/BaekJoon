/*
* 백준 11689번. GCD(n, k) = 1
* https://www.acmicpc.net/problem/11689
*/

private var n = -1L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val bw = System.`in`.bufferedReader()
    n = bw.readLine().toLong()
    bw.close()
}

private fun getResult(): Long {
    var result = n

    var i = 2L
    while (i * i <= n) {
        if (n % i == 0L) {
            result = (result / i) * (i - 1)

            while (n % i == 0L) {
                n /= i
            }
        }

        i++
    }

    return if (n == 1L) result else result / n * (n - 1)
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
