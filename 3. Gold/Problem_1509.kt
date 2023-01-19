import kotlin.math.min

/*
* 백준 1509번. 팰린드롬 분할
* https://www.acmicpc.net/problem/1509
*/

private fun main() {
    val str = readln()
    val result = getResult(str)
    printResult(result)
}

private fun getResult(str: String): Int {

    val dp = IntArray(size = str.length + 1) { 0 }
    val isPalindrome = getPalindromeList(str)

    for (end in str.indices) {
        dp[end + 1] = dp[end] + 1
        for (start in 0..end) {
            if (isPalindrome[start][end]) {
                dp[end + 1] = min(dp[end + 1], dp[start] + 1)
            }
        }
    }

    return dp[str.length]
}

private fun getPalindromeList(str: String): Array<BooleanArray> {

    val isPalindrome = Array(size = str.length) { BooleanArray(size = str.length) { false } }

    // 길이 1짜리 팰린드롬
    for (i in isPalindrome.indices) {
        isPalindrome[i][i] = true
    }

    // 길이 2짜리 팰린드롬
    for (i in 0 until isPalindrome.size - 1) {
        if (str[i] == str[i + 1]) isPalindrome[i][i + 1] = true
    }

    // 길이 3이상 팰린드롬
    for (length in 3..str.length) {
        for (start in 0 until str.length - length + 1) {
            val end = start + length - 1
            if (str[start] == str[end] && isPalindrome[start + 1][end - 1]) {
                isPalindrome[start][end] = true
            }
        }
    }

    return isPalindrome
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
