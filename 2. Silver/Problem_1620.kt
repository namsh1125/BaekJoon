/*
* 백준 1620번. 나는야 포켓몬 마스터 이다솜
* https://www.acmicpc.net/problem/1620
*/

private val encyclopedia = HashMap<String, String>()
private val question = arrayListOf<String>()

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    repeat(n) {
        val id = (it + 1).toString()
        val name = br.readLine()
        encyclopedia[id] = name
        encyclopedia[name] = id
    }

    repeat(m) {
        question.add(br.readLine())
    }
}

fun getResult(): ArrayList<String> {

    val result = arrayListOf<String>()

    question.forEach {
        result.add(encyclopedia[it]!!)
    }

    return result
}

fun printResult(result: ArrayList<String>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
