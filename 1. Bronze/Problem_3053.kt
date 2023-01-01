/*
* 백준 3053번. 택시 기하학
* https://www.acmicpc.net/problem/3053
*/

fun main() {

    val br = System.`in`.bufferedReader()
    val r = br.readLine().toInt()

    val bw = System.out.bufferedWriter()
    bw.write("${Math.PI * r * r}\n")
    bw.write("${2 * r * r}\n")
    bw.flush()
    bw.close()
}
