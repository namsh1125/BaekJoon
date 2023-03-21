/*
* 백준 1026번. 보물
* https://www.acmicpc.net/problem/1026
*/

private var n = -1
private lateinit var a: List<Int>
private lateinit var b: List<Int>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    n = br.readLine().toInt()
    a = br.readLine().split(' ').map { it.toInt() }
    b = br.readLine().split(' ').map { it.toInt() }

    br.close()
}

private fun getResult(): Int {
    a = a.sorted()
    b = b.sortedDescending()

    var result = 0
    for (i in 0 until n) {
        result += a[i] * b[i]
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
