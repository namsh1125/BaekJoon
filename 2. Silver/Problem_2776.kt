import java.util.TreeMap

/*
* 백준 2776번. 암기왕
* https://www.acmicpc.net/problem/2776
*/

fun main() {
    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()

    val result = StringBuffer()
    repeat(tc) {
        br.readLine()
        val note1 = TreeMap<Int, Boolean>()
        br.readLine().split(' ').map { it.toInt() }.forEach {
            note1[it] = true
        }

        br.readLine()
        val check = br.readLine().split(' ').map { it.toInt() }
        result.append(getResult(note1, check))
    }

    printResult(result.toString())
}

fun getResult(note1: TreeMap<Int, Boolean>, check: List<Int>): StringBuffer {
    val result = StringBuffer()
    check.forEach {
        when (note1.contains(it)) {
            true -> result.append("1\n")
            false -> result.append("0\n")
        }
    }

    return result
}

fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write(result)
    bw.flush()
    bw.close()
}
