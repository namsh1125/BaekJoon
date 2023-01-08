/*
* 백준 1764번. 듣보잡
* https://www.acmicpc.net/problem/1764
*/

fun main() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    val person = HashMap<String, Int>()
    val result = arrayListOf<String>()

    repeat(n) {
        person[br.readLine()] = 1
    }

    repeat(m) {
        val name = br.readLine()
        if (person.containsKey(name)) {
            result.add(name)
        }
    }

    result.sort()

    printResult(result)
}

fun printResult(result: ArrayList<String>) {

    val bw = System.out.bufferedWriter()
    bw.write("${result.size}\n")
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
