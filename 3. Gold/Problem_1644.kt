/*
* 백준 1644번. 소수의 연속합
* https://www.acmicpc.net/problem/1644
*/

private fun main() {
    val n = readln().toInt()
    val result = getResult(n)
    printResult(result)
}

private fun getResult(n: Int): Int {
    val primeNumber = getPrimeNumber(n)
    val prefixSum = getPrefixSum(primeNumber)
    return getResult(n, prefixSum)
}

private fun getPrimeNumber(n: Int): ArrayList<Int> {

    val primeNumber = arrayListOf<Int>()
    val isPrime = BooleanArray(size = n + 1) { true }

    if (n == 1) return primeNumber

    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..n) {
        if (!isPrime[i]) continue
        else {
            primeNumber.add(i)
            for (j in i * 2..n step (i)) {
                isPrime[j] = false
            }
        }
    }

    return primeNumber
}

private fun getPrefixSum(primeNumber: ArrayList<Int>): ArrayList<Int> {

    val prefixSum = arrayListOf<Int>()

    prefixSum.add(0)
    for (i in primeNumber.indices) {
        prefixSum.add(prefixSum[i] + primeNumber[i])
    }

    return prefixSum
}

private fun getResult(n: Int, prefixSum: ArrayList<Int>): Int {

    var result = 0
    var leftPointer = 0
    var rightPointer = 0

    while (leftPointer < prefixSum.size && rightPointer < prefixSum.size) {

        val sum = prefixSum[rightPointer] - prefixSum[leftPointer]
        if (sum == n) result++

        if (sum < n) rightPointer++
        else leftPointer++
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
