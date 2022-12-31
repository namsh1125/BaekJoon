import kotlin.math.pow

/*
* 백준 1009번. 분산처리
* https://www.acmicpc.net/problem/1009
*/

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val result = arrayListOf<Int>()

    for (i in 0 until n) {

        var (a, b) = br.readLine().split(' ').map { it.toInt() }

        a %= 10 // 구하는 수는 일의 자리이므로 일의 자리만 신경 쓴다.
        b = b % 4 + 4 // 모든 수는 자기 자신을 곱했을 때 마지막 자리가 4번의 주기로 반복된다.

        val computer = a.toDouble().pow(b.toDouble()).toInt() % 10

        if (computer == 0) result.add(10)
        else result.add(computer)
    }

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
