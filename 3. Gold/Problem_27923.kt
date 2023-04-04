import kotlin.math.log2

/*
* 백준 27923번. 햄버거최대 몇개드실수있나요?
* https://www.acmicpc.net/problem/27923
*/

private var n = -1 // 준비한 햄버거의 개수
private val burger = ArrayDeque<Int>() // 햄버거 질량. 질량을 기준으로 내림차순

private var k = -1 // 준비한 콜라의 개수
private lateinit var time: IntArray // 콜라를 마시는 시기

private var l = -1 // 콜라 효과의 지속 시간

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        k = it[1]
        l = it[2]
    }

    br.readLine().split(' ').map { it.toInt() }.forEach { mass ->
        burger.add(mass)
    }

    time = IntArray(n + 1) { 0 }
    br.readLine().split(' ').map { it.toInt() }.forEach { i ->
        time[i]++
    }

    br.close()
}

private fun getResult(): Long {
    val cola = ArrayDeque<Int>() // 콜라 버프 적용 갯수

    val prefixSum = IntArray(n + 1) { 0 }
    for (i in 1 until prefixSum.size) {
        prefixSum[i] = prefixSum[i - 1] + time[i]
    }

    for (i in 1 until prefixSum.size) {
        when {
            i <= l -> cola.add(prefixSum[i])
            else -> cola.add(prefixSum[i] - prefixSum[i - l])
        }
    }

    // 콜라 버프가 가장 많이 있을 때 가장 질량이 많은 햄버거를 먹어야 위의 용량을 줄일 수 있다.
    burger.sortDescending()
    cola.sortDescending()

    var result = 0L
    for (i in 0 until burger.size) {
        if (cola[i] > log2(1_000_000_000.0)) continue
        else result += burger[i] / power(cola[i])
    }

    return result
}

private fun power(time: Int): Int {
    return if (time == 0) 1
    else if (time % 2 == 1) 2 * power(time - 1)
    else {
        val half = power(time / 2)
        half * half
    }
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
