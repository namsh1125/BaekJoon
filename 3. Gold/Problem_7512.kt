/*
* 백준 7512번. 연속하는 소수의 합
* https://www.acmicpc.net/problem/7512
*/

private const val MAX = 10_000_000
private val isPrime = BooleanArray(MAX) { true }
private val primeList = ArrayList<Int>()

val sb = StringBuilder()

private fun main() {
    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()

    initPrimeNumber()

    repeat(tc) { time ->
        val m = br.readLine().toInt()
        val arr = br.readLine().split(' ').map { it.toInt() }
        sb.append("Scenario ${time + 1}:\n${getResult(m, arr)}\n\n")
    }
    br.close()

    printResult()
}

private fun initPrimeNumber() {
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2 until isPrime.size) {
        if (isPrime[i]) {
            primeList.add(i)
            for (j in i * 2 until isPrime.size step i) {
                isPrime[j] = false
            }
        }
    }
}

private fun getResult(m: Int, arr: List<Int>): Int {
    val begin = IntArray(arr.size) { 0 }
    val sum = IntArray(arr.size) { 0 }
    arr.forEachIndexed { index, count ->
        for (i in 0 until count) {
            sum[index] += primeList[i]
        }

        while (!isPrime[sum[index]]) {
            begin[index]++
            val end = begin[index] + count - 1
            sum[index] = sum[index] + primeList[end] - primeList[begin[index] - 1]
        }
    }

    var result = sum.max()
    while (!same(sum, result)) {
        for (index in 0 until m) {
            if (sum[index] < result) {
                do {
                    begin[index]++
                    val end = begin[index] + arr[index] - 1
                    sum[index] = sum[index] + primeList[end] - primeList[begin[index] - 1]
                } while (!isPrime[sum[index]])

            } else result = sum[index]
        }
    }

    return result
}

private fun same(sum: IntArray, value: Int): Boolean {
    sum.forEach {
        if (it != value) return false
    }
    return true
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write(sb.toString())
    bw.flush()
    bw.close()
}
