/*
* 백준 11723번. 집합
* https://www.acmicpc.net/problem/11723
*/

private fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val result = ArrayDeque<Int>()
    var s = ArrayDeque<Int>()
    repeat(n) {
        val line = br.readLine().split(' ')

        when (line[0]) {
            "add" -> if (!s.contains(line[1].toInt())) s.add(line[1].toInt())
            "remove" -> if (s.contains(line[1].toInt())) s.remove(line[1].toInt())
            "check" -> if (s.contains(line[1].toInt())) result.add(1) else result.add(0)
            "toggle" -> if (s.contains(line[1].toInt())) s.remove(line[1].toInt()) else s.add(line[1].toInt())
            "all" -> {
                s.clear()
                for (i in 1..20) s.add(i)
            }
            "empty" -> s.clear()
        }
    }
    br.close()

    printResult(result)
}

private fun printResult(result: ArrayDeque<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
