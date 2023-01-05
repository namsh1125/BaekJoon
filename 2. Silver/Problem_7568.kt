/*
* 백준 7568번. 덩치
* https://www.acmicpc.net/problem/7568
*/

data class Person(val weight: Int, val height: Int, var rank: Int)

private val personList = arrayListOf<Person>()

fun main() {

    initVariable()
    rank()
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    for (i in 0 until n) {

        val (w, h) = br.readLine().split(' ').map { it.toInt() }
        personList.add(Person(w, h, -1))
    }
}

fun rank() {

    for (i in personList.indices) {

        var lose = 0
        for (j in personList.indices) {

            if (i == j) continue
            if (personList[i].height < personList[j].height && personList[i].weight < personList[j].weight) lose++
        }
        personList[i].rank = lose + 1
    }
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    personList.forEach {
        bw.write("${it.rank} ")
    }
    bw.flush()
    bw.close()
}
