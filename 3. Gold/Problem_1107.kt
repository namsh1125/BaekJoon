import kotlin.math.abs
import kotlin.math.min
import java.util.StringTokenizer

/*
* 백준 1107번. 리모컨
* https://www.acmicpc.net/problem/1107
*/

val broken = BooleanArray(size = 10) { false }

const val maxChannel = 1_000_000

fun main() {

    val num = initVariable()
    val result = getResult(num)
    printResult(result)
}

fun initVariable(): Int {

    val br = System.`in`.bufferedReader()

    val num = br.readLine().toInt()
    val m = br.readLine().toInt()

    if (m != 0) {
        val st = StringTokenizer(br.readLine())
        repeat(m) {
            broken[st.nextToken().toInt()] = true
        }
    }

    return num
}

fun getResult(num: Int): Int {

    var result = abs(num - 100)
    for (i in 0 until maxChannel) {

        val str = i.toString()
        var isBreak = false

        for (j in str.indices) {
            if (broken[str[j] - '0']) {
                isBreak = true
                break
            }
        }

        if (!isBreak) {
            result = min(result, abs(num - i) + str.length)
        }
    }

    return result
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
