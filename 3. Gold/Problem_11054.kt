import kotlin.math.max

/*
* 백준 11054번. 가장 긴 바이토닉 부분 수열
* https://www.acmicpc.net/problem/11054
*/

private fun main() {

    val notation = initVariable()
    val result = getResult(notation)
    printResult(result)
}

private fun initVariable(): List<Int> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val s = br.readLine().split(' ').map { it.toInt() }

    return s
}

private fun getResult(s: List<Int>): Int {

    val n = s.size
    val increase = IntArray(size = n) { 1 }
    val decrease = IntArray(size = n) { 1 }
    val dp = IntArray(size = n) { 1 }

    for (i in 1 until n) {
        for (j in 0 until i) {
            if (s[i] > s[j]) {
                increase[i] = max(increase[i], increase[j] + 1)
            }
        }
    }

    for (i in n - 1 downTo 0) {
        for (j in n - 1 downTo i) {
            if (s[i] > s[j]) {
                decrease[i] = max(decrease[i], decrease[j] + 1)
            }
        }
    }


    for (i in 0 until n) {
        dp[i] = increase[i] + decrease[i] - 1 // 본인 제외
    }

    return dp.max()
}

private fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
