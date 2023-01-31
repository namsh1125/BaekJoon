import java.util.PriorityQueue
import kotlin.math.abs

/*
* 백준 11286번. 절댓값 힙
* https://www.acmicpc.net/problem/11286
*/

private val calc = ArrayList<Int>()
private val result = ArrayList<Int>()

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    repeat(n) {
        calc.add(br.readLine().toInt())
    }

    br.close()
}

private fun getResult() {
    val queue = PriorityQueue<Int> { o1, o2 ->
        val abs1 = abs(o1)
        val abs2 = abs(o2)

        when {
            abs1 == abs2 -> o1 - o2
            else -> abs1 - abs2
        }
    }

    calc.forEach { x ->
        if (x == 0) {
            if (queue.isEmpty()) result.add(0)
            else result.add(queue.remove())
        } else {
            queue.add(x)
        }
    }
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
