/*
* 백준 2231번. 분해합
* https://www.acmicpc.net/problem/2231
*/

fun main() {

    val num = initVariable()
    val result = getResult(num)
    printResult(result)
}

fun initVariable(): Int {

    val br = System.`in`.bufferedReader()
    return br.readLine().toInt()
}

fun getResult(num: Int): Int {

    for (i in 1 until num) {
        if (num == getBunhaehab(i)) {
            return i
        }
    }

    return 0
}

fun getBunhaehab(num: Int): Int {

    var bunhaehab = num

    var tmp = num
    while (tmp != 0) {
        bunhaehab += tmp % 10
        tmp /= 10
    }

    return bunhaehab
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
