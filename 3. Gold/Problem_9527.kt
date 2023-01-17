/*
* 백준 9527번. 1의 개수 세기
* https://www.acmicpc.net/problem/9527
*/

private fun main() {
    val (a, b) = readln().split(' ').map { it.toLong() }
    val result = getResult(a, b)
    printResult(result)
}

private fun getResult(a: Long, b: Long): Long {
    val prefixSum = getPrefixSum()
    return getCount(b, prefixSum) - getCount(a - 1, prefixSum)
}

private fun getPrefixSum(): LongArray {

    val prefixSum = LongArray(size = 55) { 1L } // 입력의 최대값인 10^16의 비트 길이는 54

    for (i in 1 until prefixSum.size) {
        prefixSum[i] = 2 * prefixSum[i - 1] + (1L shl i)
    }

    return prefixSum
}

private fun getCount(n: Long, prefixSum: LongArray): Long {

    var num = n
    var count = num and 1 // 일의 자리

    for (i in prefixSum.size - 1 downTo 1) { // 십의 자리까지
        if ((num and (1L shl i)) > 0L) { // n의 i번째 bit가 1이면
            count += prefixSum[i - 1] + (num - (1L shl i) + 1)
            num -= (1L shl i)
        }
    }

    return count
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
