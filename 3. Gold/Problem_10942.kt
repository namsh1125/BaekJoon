/*
* 백준 10942번. 팰린드롬?
* https://www.acmicpc.net/problem/10942
*/

private fun main() {

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    br.readLine()

    val arr = br.readLine().split(' ').map { it.toInt() }
    val isPalindrome = getPalindromeList(arr)

    val n = br.readLine().toInt()
    repeat(n) {
        val (start, end) = br.readLine().split(' ').map { it.toInt() - 1 }
        when (isPalindrome[start][end]) {
            true -> bw.write("1\n")
            false -> bw.write("0\n")
        }
    }
    bw.flush()
    bw.close()
}

private fun getPalindromeList(arr: List<Int>): Array<BooleanArray> {

    val isPalindrome = Array(size = arr.size) { BooleanArray(size = arr.size) { false } }

    // 길이 1짜리 팰린드롬
    for (i in isPalindrome.indices) {
        isPalindrome[i][i] = true
    }

    // 길이 2짜리 팰린드롬
    for (i in 0 until isPalindrome.size - 1) {
        if (arr[i] == arr[i + 1]) isPalindrome[i][i + 1] = true
    }

    // 길이 3이상 팰린드롬
    for (length in 3..arr.size) {
        for (start in 0 until arr.size - length + 1) {
            val end = start + length - 1
            if (arr[start] == arr[end] && isPalindrome[start + 1][end - 1]) {
                isPalindrome[start][end] = true
            }
        }
    }

    return isPalindrome
}
