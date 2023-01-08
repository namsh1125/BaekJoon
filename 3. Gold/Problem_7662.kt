import java.util.TreeMap

/*
* 백준 7662번. 이중 우선순위 큐
* https://www.acmicpc.net/problem/7662
*/

data class Calculate(val calc: Char, val num: Long)

private lateinit var testcaseList: Array<ArrayList<Calculate>>

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    testcaseList = Array(size = t) { arrayListOf() }

    for (i in testcaseList.indices) {

        val k = br.readLine().toInt()
        repeat(k) {
            val line = br.readLine().split(' ')
            testcaseList[i].add(Calculate(line[0][0], line[1].toLong()))
        }
    }

}

fun getResult(): ArrayList<String> {

    val result = arrayListOf<String>()

    for (i in testcaseList.indices) {
        result.add(getResult(i))
    }

    return result
}

fun getResult(i: Int): String {

    val tm = TreeMap<Long, Int>()
    testcaseList[i].forEach {

        when (it.calc) {
            'I' -> tm[it.num] = tm.getOrDefault(it.num, 0) + 1
            'D' -> {
                if (tm.isNotEmpty()) {
                    val key = if (it.num == 1L) {
                        tm.lastKey()
                    } else {
                        tm.firstKey()
                    }

                    if (tm[key] == 1) tm.remove(key)
                    else tm[key] = tm[key]!! - 1
                }
            }
        }
    }

    return if (tm.isEmpty()) {
        "EMPTY"
    } else {
        "${tm.lastKey()} ${tm.firstKey()}"
    }
}

fun printResult(result: ArrayList<String>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
