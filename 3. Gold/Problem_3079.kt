/*
* 백준 3079번. 입국심사
* https://www.acmicpc.net/problem/3079
*/

private var n = -1 // 입국 심사대
private lateinit var time: LongArray

private var m = -1 // 친구

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
    }

    time = LongArray(n) { 0 }
    repeat(n) { i ->
        time[i] = br.readLine().toLong()
    }

    br.close()
}

private fun getResult(): Long {
    var start = 1L
    var end = time.maxOrNull()!! * m

    while (start <= end) {
        val mid = (start + end) / 2

        var pass = 0L
        for (i in time.indices) {
            pass += mid / time[i]
            if (pass > m) break
        }

        when {
            pass >= m -> end = mid - 1
            pass < m -> start = mid + 1
        }
    }

    return start
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
