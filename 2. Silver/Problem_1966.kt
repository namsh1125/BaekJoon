import java.util.StringTokenizer

/*
* 백준 1966번. 프린터 큐
* https://www.acmicpc.net/problem/1966
*/

data class TestCase(val documentList: ArrayDeque<Document>, val question: Int)
data class Document(val number: Int, val priority: Int)

private val testCaseList = ArrayList<TestCase>()

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    repeat(n) {

        val (a, b) = br.readLine().split(' ').map { it.toInt() }

        val documentList = ArrayDeque<Document>()
        val st = StringTokenizer(br.readLine())
        for (i in 0 until a) {
            documentList.add(Document(i, st.nextToken().toInt()))
        }

        testCaseList.add(TestCase(documentList, b))
    }
}

fun getResult(): ArrayList<Int> {

    val result = arrayListOf<Int>()
    repeat(testCaseList.size) {
        result.add(getResult(it))
    }

    return result
}

fun getResult(i: Int): Int {

    val testCase = testCaseList[i]
    val documentList = testCase.documentList
    val question = testCase.question

    var print = 0
    while (documentList.isNotEmpty()) {

        val document = documentList.removeFirst()
        var highPriority = true

        for (i in documentList.indices) {
            if (document.priority < documentList[i].priority) {
                highPriority = false
                documentList.add(document)
                break
            }
        }

        if (highPriority == false) continue
        else print++

        if (document.number == testCase.question) return print
    }

    return -1
}

fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
