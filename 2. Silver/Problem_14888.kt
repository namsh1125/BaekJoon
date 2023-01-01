import java.lang.Integer.max
import java.lang.Integer.min

/*
* 백준 14888번. 연산자 끼워넣기
* https://www.acmicpc.net/problem/14888
*/

private lateinit var num: ArrayList<Int>
private lateinit var operatorNum: ArrayList<Int>
private var maxValue = Integer.MIN_VALUE
private var minValue = Integer.MAX_VALUE

fun main() {

    initVariable()
    dfs(1, num[0])
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    br.readLine()

    num = br.readLine().split(' ').map { it.toInt() } as ArrayList
    operatorNum = br.readLine().split(' ').map { it.toInt() } as ArrayList
}

fun dfs(index: Int, result: Int) {

    if (index == num.size) {
        maxValue = max(maxValue, result)
        minValue = min(minValue, result)
        return
    }

    for (i in 0 until 4) {

        if (operatorNum[i] == 0) continue

        var calculation = result

        operatorNum[i]--
        when (i) {
            0 -> calculation += num[index]
            1 -> calculation -= num[index]
            2 -> calculation *= num[index]
            else -> {
                if (calculation >= 0) calculation /= num[index]
                else calculation = (-1 * calculation / num[index]) * -1
            }
        }

        dfs(index + 1, calculation)
        operatorNum[i]++
    }

}

fun printResult() {

    val bw = System.out.bufferedWriter()
    bw.write("$maxValue\n$minValue\n")
    bw.flush()
    bw.close()
}
