/*
* 백준 9461번. 파도반 수열
* https://www.acmicpc.net/problem/9461
*/

private val length = LongArray(100) { 0 }

private fun main() {

    initVariable()

    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()
    val result = ArrayList<Long>()

    repeat(tc) {
        val n = br.readLine().toInt() -1
        result.add(length[n])
    }
    br.close()

    printResult(result)
}

private fun initVariable() {
    length[0] = 1
    length[1] = 1
    length[2] = 1
    length[3] = 2
    length[4] = 2

    for(i in 5 until length.size) {
        length[i] = length[i-1] + length[i-5]
    }
}

private fun printResult(result: ArrayList<Long>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
