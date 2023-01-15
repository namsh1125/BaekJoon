/*
* 백준 9019번. DSLR
* https://www.acmicpc.net/problem/9019
*/

data class Num(val value: Int, val command: String)

private fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val result = arrayListOf<String>()

    repeat(n) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        result.add(getResult(a, b))
    }

    printResult(result)
}

fun getResult(from: Int, to: Int): String {

    val visited = BooleanArray(size = 10000) { false }

    val queue = ArrayDeque<Num>()
    queue.add(Num(from, ""))

    while (queue.isNotEmpty()) {

        val top = queue.removeFirst()

        if (top.value == to) return top.command

        val d = commandD(top.value)
        if (!visited[d]) {
            queue.add(Num(d, top.command + "D"))
            visited[d] = true
        }

        val s = commandS(top.value)
        if (!visited[s]) {
            queue.add(Num(s, top.command + "S"))
            visited[s] = true
        }

        val l = commandL(top.value)
        if (!visited[l]) {
            queue.add(Num(l, top.command + "L"))
            visited[l] = true
        }

        val r = commandR(top.value)
        if (!visited[r]) {
            queue.add(Num(r, top.command + "R"))
            visited[r] = true
        }
    }

    return ""
}

private fun commandD(n: Int): Int {
    return n * 2 % 10000
}

private fun commandS(n: Int): Int {
    return if (n == 0) 9999 else n - 1
}

private fun commandL(n: Int): Int {
    return n % 1000 * 10 + n / 1000
}

private fun commandR(n: Int): Int {
    return n % 10 * 1000 + n / 10
}

private fun printResult(result: ArrayList<String>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
