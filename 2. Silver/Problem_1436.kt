/*
* 백준 1436번. 영화감독 숌
* https://www.acmicpc.net/problem/1436
*/

fun main() {

    val n = initVariable()
    val result = getResult(n)
    printResult(result)
}

fun initVariable(): Int {

    val br = System.`in`.bufferedReader()
    return br.readLine().toInt()
}

fun getResult(n: Int): Int {

    val num = arrayListOf<Int>()
    var i = 666
    while (num.size != n) {
        if (i.toString().contains("666")) num.add(i)
        i++
    }

    return num.last()
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
