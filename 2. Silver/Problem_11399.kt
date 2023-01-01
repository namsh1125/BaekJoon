/*
* 백준 11399번. ATM
* https://www.acmicpc.net/problem/11399
*/

fun main() {

    val time = initVariable()
    val result = getResult(time)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()
    br.readLine()

    return br.readLine().split(' ').map { it.toInt() } as ArrayList
}

fun getResult(time: ArrayList<Int>): Int {

    time.sort()

    var result = 0
    for(i in time.indices) {
        result += time[i] * (time.size - i)
    }

    return result
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
