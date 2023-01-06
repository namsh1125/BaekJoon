/*
* 백준 14003번. 가장 긴 증가하는 부분 수열 5
* https://www.acmicpc.net/problem/14003
*/

fun main() {

    val sequence = initVariable()
    val result = getResult(sequence)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val sequence = br.readLine().split(' ').map { it.toInt() } as ArrayList

    return sequence
}

fun getResult(sequence: ArrayList<Int>): List<Int> {

    val lis = arrayListOf(sequence[0])
    val dp = Array(size = sequence.size) { 1 }

    for (i in 1 until sequence.size) {

        // LIS의 마지막 값보다 큰 경우 추가
        if (lis.last() < sequence[i]) {
            dp[i] = lis.size + 1
            lis.add(sequence[i])
        }

        // 그렇지 않은 경우 대치 가능한 곳을 찾아 대치
        else {
            var start = 0
            var end = lis.size

            while (start < end) {

                val mid = (start + end) / 2

                if (lis[mid] < sequence[i]) start = mid + 1
                else end = mid
            }

            lis[start] = sequence[i]
            dp[i] = start + 1
        }
    }

    val result = arrayListOf<Int>()
    var len = dp.max()
    for (i in dp.size - 1 downTo 0) {

        if (dp[i] == len) {
            result.add(sequence[i])
            len--
        }
    }

    return result.reversed()
}

fun printResult(result: List<Int>) {

    val bw = System.out.bufferedWriter()
    bw.write("${result.size}\n")
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
