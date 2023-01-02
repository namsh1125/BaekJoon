import java.util.StringTokenizer
import kotlin.math.ceil

/*
* 백준 2869번. 달팽이는 올라가고 싶다
* https://www.acmicpc.net/problem/2869
*/

private var climb = 0L
private var slip = 0L
private var height = 0L

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    climb = st.nextToken().toLong()
    slip = st.nextToken().toLong()
    height = st.nextToken().toLong()
}

fun getResult(): Long {
    return ceil((height - climb).toDouble() / (climb - slip).toDouble()).toLong() + 1L
}

fun printResult(result: Long) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
