import java.util.PriorityQueue

/*
* 백준 2661번. 좋은수열
* https://www.acmicpc.net/problem/2661
*/

private var n = -1

private const val UNKNOWN = ""

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    br.close()
}

private fun getResult(): String {
    val queue = PriorityQueue(Comparator<String> { o1, o2 -> o2.length - o1.length })
    queue.add("")

    while (queue.isNotEmpty()) {
        val str = queue.remove()

        for (i in 1..3) {
            val new = str + "$i"

            if (goodPermutation(new)) {
                if (new.length == n) return new
                queue.add(new)
            }
        }
    }

    return ""
}

private fun goodPermutation(str: String): Boolean {
    for (i in 1..str.length / 2) {
        val left = str.substring(str.length - 2 * i, str.length - i)
        val right = str.substring(str.length - i, str.length)

        if (left == right) return false
    }

    return true
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
