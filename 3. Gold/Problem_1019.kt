/*
* 백준 1019번. 책 페이지
* https://www.acmicpc.net/problem/1019
*/

private val result = IntArray(10) { 0 }
private var end = -1

private fun main() {
    initVariable()
    getResult()
    printResult()
}

private fun initVariable() {
    val bw = System.`in`.bufferedReader()
    end = bw.readLine().toInt()
    bw.close()
}

private fun getResult() {
    var point = 1
    var start = 1

    while (start <= end) {
        // 끝자리를 9로 만들기
        while (end % 10 != 9 && start <= end) {
            cal(end, point)
            end--
        }

        if (end < start) break

        // 끝자리를 0으로 만들기
        while (start % 10 != 0 && start <= end) {
            cal(start, point)
            start++
        }

        start /= 10
        end /= 10

        for (i in 0..9) {
            result[i] += (end - start + 1) * point
        }

        point *= 10
    }
}

private fun cal(i: Int, point: Int) {
    if (i > 0) {
        result[i % 10] += point
        cal(i / 10, point)
    }
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
