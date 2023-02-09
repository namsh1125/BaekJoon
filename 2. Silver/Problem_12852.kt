/*
* 백준 12852번. 1로 만들기 2
* https://www.acmicpc.net/problem/12852
*/

private const val MAX = 1_000_000 + 1
private const val NOTVISITED = Int.MAX_VALUE

private fun main() {
    val n = initVariable()
    val result = getResult(n)
    printResult(result)
}

private fun initVariable(): Int {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    br.close()
    return n
}

private fun getResult(n: Int): List<Int> {
    val dp = IntArray(MAX) { Int.MAX_VALUE }
    val queue = ArrayDeque<Int>()

    dp[n] = 0
    queue.add(n)
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        if (cur == 1) break

        if (cur % 3 == 0 && dp[cur / 3] == NOTVISITED) {
            dp[cur / 3] = dp[cur] + 1
            queue.add(cur / 3)
        }
        if (cur % 2 == 0 && dp[cur / 2] == NOTVISITED) {
            dp[cur / 2] = dp[cur] + 1
            queue.add(cur / 2)
        }
        if (cur - 1 >= 0 && dp[cur - 1] == NOTVISITED) {
            dp[cur - 1] = dp[cur] + 1
            queue.add(cur - 1)
        }
    }

    val result = ArrayList<Int>()
    result.add(1)

    var num = 1
    while (num != n) {
        if (num * 3 < MAX && dp[num * 3] == dp[num] - 1) {
            result.add(num * 3)
            num *= 3
        } else if (num * 2 < MAX && dp[num * 2] == dp[num] - 1) {
            result.add(num * 2)
            num *= 2
        } else if (num + 1 < MAX) {
            result.add(num + 1)
            num += 1
        }
    }

    return result.reversed()
}

private fun printResult(result: List<Int>) {
    val bw = System.out.bufferedWriter()

    bw.write("${result.size - 1}\n")
    result.forEach {
        bw.write("$it ")
    }

    bw.flush()
    bw.close()
}
