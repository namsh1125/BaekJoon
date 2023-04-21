import java.util.Stack

/*
* 백준 3015번. 오아시스 재결합
* https://www.acmicpc.net/problem/3015
*/

data class Pair(val height: Int, var count: Int)

private var n = -1
private lateinit var height: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    height = IntArray(n)
    repeat(n) { i ->
        height[i] = br.readLine().toInt()
    }

    br.close()
}

private fun getResult(): Long {
    var result = 0L

    val stack = Stack<Pair>()
    for (i in 0 until n) {
        val pair = Pair(height[i], 1)

        while (stack.isNotEmpty() && stack.peek().height <= height[i]) {
            val pop = stack.pop()
            result += pop.count

            if (pop.height == height[i]) { // 동일 키라면 갯수 추가
                pair.count += pop.count
            }
        }

        if (stack.isNotEmpty()) result++ // 현재 키보다 큰 값 존재하는 경우

        stack.push(pair)
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
