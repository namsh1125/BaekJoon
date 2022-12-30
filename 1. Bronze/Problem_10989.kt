/*
* 백준 10989번. 수 정렬하기 3
* https://www.acmicpc.net/problem/10989
*/

fun main() {

    val br = System.`in`.bufferedReader()

    var n = br.readLine().toInt()
    val num = Array(size = 10001) { 0 }

    for (i in 0 until n) {
        num[br.readLine().toInt()]++
    }

    val bw = System.out.bufferedWriter()

    for(i in 1 .. 10000) {
        while(num[i] > 0) {
            bw.write("$i\n")
            num[i]--
        }
    }

    bw.flush()
    bw.close()
}
