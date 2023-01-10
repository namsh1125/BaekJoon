import java.util.StringTokenizer
import kotlin.math.pow

/*
* 백준 1074번. Z
* https://www.acmicpc.net/problem/1074
*/

private var size = -1
private var r = -1
private var c = -1

private fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    size = 2.0.pow(n).toInt()

    r = st.nextToken().toInt()
    c = st.nextToken().toInt()
}

private fun getResult(): Int {

    var result = 0

    while (size != 0) {

        size /= 2

        // 1사분면
        if (r < size && c < size) {
            result += size * size * 0
        }

        // 2사분면
        else if (r < size && c >= size) {
            result += size * size * 1
            c -= size
        }

        // 3사분면
        else if (r >= size && c < size) {
            result += size * size * 2
            r -= size
        }

        // 4사분면
        else {
            result += size * size * 3
            r -= size
            c -= size
        }
    }

    return result
}

private fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
