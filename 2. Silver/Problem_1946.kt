/*
* 백준 1946번. 신입 사원
* https://www.acmicpc.net/problem/1946
*/

data class Person(val document: Int, val interview: Int, var fail: Boolean)

private fun main() {
    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()
    val result = ArrayList<Int>()

    repeat(tc) {
        val n = br.readLine().toInt()
        val personList = ArrayList<Person>()

        repeat(n) {
            val (a, b) = br.readLine().split(' ').map { it.toInt() }
            personList.add(Person(a, b, false))
        }

        result.add(getResult(personList))
    }
    br.close()

    printResult(result)
}

private fun getResult(personList: ArrayList<Person>): Int {
    personList.sortBy { it.document }

    var interviewScore = Int.MAX_VALUE
    for (person in personList) {
        if (interviewScore > person.interview) interviewScore = person.interview
        else person.fail = true
    }

    personList.sortBy { it.interview }

    var documentScore = Int.MAX_VALUE
    for (person in personList) {
        if (person.fail) continue
        if (documentScore > person.document) documentScore = person.document
        else person.fail = true
    }

    var result = 0
    for (person in personList) {
        if (!person.fail) result++
    }

    return result
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
