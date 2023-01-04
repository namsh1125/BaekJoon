import java.lang.Math.sqrt

/*
* 백준 2839번. 설탕 배달
* https://www.acmicpc.net/problem/2839
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

    for (i in n / 5 downTo 0) {
        if ((n - i * 5) % 3 == 0) return i + (n - i * 5) / 3
    }

    return -1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
