import java.util.StringTokenizer

/*
* 백준 16953번. A → B
* https://www.acmicpc.net/problem/16953
*/

data class Num(val value: Long, val time: Int)

private var a = -1L
private var b = -1L

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    a = st.nextToken().toLong()
    b = st.nextToken().toLong()
}

fun getResult(): Int {

    val queue = ArrayDeque<Num>()
    queue.add(Num(a, 0))

    while (queue.isNotEmpty()) {

        val num = queue.removeFirst()
        if (num.value == b) return num.time + 1

        if(num.value * 2 <= b) {
            queue.add(Num(num.value * 2, num.time + 1))
        }

        if(num.value * 10 + 1 <= b) {
            queue.add(Num(num.value * 10 + 1, num.time + 1))
        }
    }

    return -1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
