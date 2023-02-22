/*
* 백준 5341번. Pyramids
* https://www.acmicpc.net/problem/5341
*/

private fun main() {
    val br = System.`in`.bufferedReader()
    val result = arrayListOf<Int>()

    while (true) {
        val n = br.readLine().toInt()
        if (n == 0) break
        else result.add(n * (n + 1) / 2)
    }
    br.close()

    printResult(result)
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
