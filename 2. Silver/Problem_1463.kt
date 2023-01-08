/*
* 백준 1463번. 1로 만들기
* https://www.acmicpc.net/problem/1463
*/

data class Num(val x: Int, val time: Int)

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

    val queue = ArrayDeque<Num>()
    queue.add(Num(num, 0))

    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()
        if (top.x == 1) return top.time

        if (top.x % 3 == 0) queue.add(Num(top.x / 3, top.time + 1))
        if (top.x % 2 == 0) queue.add(Num(top.x / 2, top.time + 1))
        queue.add(Num(top.x - 1, top.time + 1))
    }

    return -1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
