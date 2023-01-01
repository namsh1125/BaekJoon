import java.lang.Integer.max

/*
* 백준 19947번. 투자의 귀재 배주형
* https://www.acmicpc.net/problem/19947
*/

data class Investment(val year: Int, val interestRate: Double)

private val investmentList = arrayListOf(
    Investment(1, 1.05),
    Investment(3, 1.2),
    Investment(5, 1.35),
)

private lateinit var money: Array<Int>
private var investYear = 0

fun main() {

    val money = initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()

    val (currentMoney, year) = br.readLine().split(' ').map { it.toInt() }

    investYear = year
    money = Array(size = investYear + 1) { currentMoney }
}

fun getResult(): Int {

    for (i in money.indices) {
        for (j in 0 until 3) {

            if (i < investmentList[j].year) continue
            else {
                val price = money[i - investmentList[j].year] * investmentList[j].interestRate
                money[i] = max(money[i], price.toInt())
            }
        }
    }

    return money[money.size -1]
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
