import kotlin.math.max

/*
* 백준 17208번. 카우버거 알바생
* https://www.acmicpc.net/problem/17208
*/

data class Order(val cheeseBurger: Int, val frenchFries: Int)

private var n = -1 // 주문의 수
private val orderList = ArrayList<Order>()

private var m = -1 // 주방에 남은 치즈버거 개수
private var k = -1 // 주방에 남은 감자튀김 개수

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        m = it[1]
        k = it[2]
    }

    for (i in 0 until n) {
        val (x, y) = br.readLine().split(' ').map { it.toInt() }

        if (x > m || y > k) continue // 남아있는 개수보다 더 많은 주문이면 패스
        else orderList.add(Order(x, y))
    }

    n = orderList.size

    br.close()
}

private fun getResult(): Int {
    val dp = Array(n + 1) { Array(m + 1) { IntArray(k + 1) { 0 } } } // i번째 주문까지 탐색했을 때 a개의 치즈버거와 b개의 감자튀김으로 받을 수 있는지

    for (i in 1..n) {
        val order = orderList[i - 1]

        for (a in 0..m) {
            for (b in 0..k) {
                if ((a - order.cheeseBurger >= 0) && (b - order.frenchFries >= 0)) { // 주문을 감당할 수 있다면
                    dp[i][a][b] = max(dp[i - 1][a][b], dp[i - 1][a - order.cheeseBurger][b - order.frenchFries] + 1)
                } else {
                    dp[i][a][b] = dp[i - 1][a][b]
                }
            }
        }
    }

    return dp[n][m][k]
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}