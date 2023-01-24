/*
* 백준 2143번. 두 배열의 합
* https://www.acmicpc.net/problem/2143
*/

private var t = Long.MAX_VALUE
private lateinit var arr1: List<Int>
private lateinit var arr2: List<Int>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    t = br.readLine().toLong()

    br.readLine()
    arr1 = br.readLine().split(' ').map { it.toInt() }

    br.readLine()
    arr2 = br.readLine().split(' ').map { it.toInt() }
}

private fun getResult(): Long {
    val list1 = getPrefixSum(arr1).sorted()
    val list2 = getPrefixSum(arr2).sorted()

    return getResult(list1, list2)
}

private fun getResult(list1: List<Long>, list2: List<Long>): Long {

    var leftPointer = 0
    var rightPointer = list2.size - 1
    var result = 0L

    while (leftPointer < list1.size && rightPointer >= 0) {

        val sum = list1[leftPointer] + list2[rightPointer]
        if (sum == t) {

            var leftCount = 0
            val a = list1[leftPointer]

            while (leftPointer < list1.size && list1[leftPointer] == a) {
                leftCount++
                leftPointer++
            }

            var rightCount = 0
            val b = list2[rightPointer]

            while (rightPointer >= 0 && list2[rightPointer] == b) {
                rightCount++
                rightPointer--
            }

            result += leftCount.toLong() * rightCount.toLong()
        }

        else if (sum < t) leftPointer++
        else rightPointer--
    }

    return result
}

private fun getPrefixSum(arr: List<Int>): ArrayList<Long> {

    val prefixSum = arrayListOf<Long>()

    for (i in arr.indices) {
        var sum = 0L
        for (j in i until arr.size) {
            sum += arr[j]
            prefixSum.add(sum)
        }
    }

    return prefixSum
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
