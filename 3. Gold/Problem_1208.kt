/*
* 백준 1208번. 부분수열의 합 2
* https://www.acmicpc.net/problem/1208
*/

private lateinit var arr: ArrayList<Int>
private var s = Long.MAX_VALUE

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (_, sum) = br.readLine().split(' ').map { it.toLong() }

    arr = br.readLine().split(' ').map { it.toInt() } as ArrayList
    s = sum
}

private fun getResult(): ULong {

    val left = arrayListOf<Long>()
    val right = arrayListOf<Long>()

    getSubsequence(0, arr.size / 2, 0L, left)
    getSubsequence(arr.size / 2, arr.size, 0L, right)

    left.sort()
    right.sort()

    return getCount(left, right)
}

private fun getSubsequence(index: Int, end: Int, sum: Long, list: ArrayList<Long>) {

    if (index == end) {
        list.add(sum)
        return
    }

    getSubsequence(index + 1, end, sum + arr[index], list)
    getSubsequence(index + 1, end, sum, list)
}

private fun getCount(left: ArrayList<Long>, right: ArrayList<Long>): ULong {

    var leftPointer = 0
    var rightPointer = right.size - 1
    var result: ULong = 0u

    while (leftPointer < left.size && rightPointer >= 0) {

        val sum = left[leftPointer] + right[rightPointer]
        if (sum == s) {

            var leftCount = 0
            val a = left[leftPointer]

            while (leftPointer < left.size && left[leftPointer] == a) {
                leftCount++
                leftPointer++
            }

            var rightCount = 0
            val b = right[rightPointer]

            while (rightPointer >= 0 && right[rightPointer] == b) {
                rightCount++
                rightPointer--
            }

            result += leftCount.toULong() * rightCount.toULong()
        }

        else if (sum < s) leftPointer++
        else rightPointer--
    }

    return if (s == 0L) {
        result - 1u // 공집합 제외
    } else {
        result
    }
}

private fun printResult(result: ULong) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
