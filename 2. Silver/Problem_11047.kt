/*
* 백준 11047번. 동전 0
* https://www.acmicpc.net/problem/11047
*/

private val coinList = arrayListOf<Int>()
private var money = -1

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(' ').map { it.toInt() }

    repeat(n) {
        coinList.add(br.readLine().toInt())
    }

    money = k
}

fun getResult(): Int {

    var result = 0

    for (i in coinList.size - 1 downTo 0) {
        if (money / coinList[i] > 0) {
            result += money / coinList[i]
            money %= coinList[i]
        }

        if (money == 0) return result
    }

    return -1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
