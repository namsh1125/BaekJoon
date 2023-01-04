import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

/*
* 백준 5430번. AC
* https://www.acmicpc.net/problem/5430
*/

data class Execute(val func: String, val arr: ArrayDeque<Int>)

private val resultList = ArrayList<String>()
private val executeList = ArrayList<Execute>()

fun main() {

    initVariable()
    getResult()
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    repeat(t) {

        val func = br.readLine()
        val count = br.readLine().toInt()
        val numList = br.readLine().replace("[","").replace("]","")

        val arr = ArrayDeque<Int>()
        if (count > 0) {
            numList.split(',').map {
                arr.add(it.toInt())
            }
        }

        executeList.add(Execute(func, arr))
    }
}

fun getResult() {
    repeat(executeList.size) {
        resultList.add(executeFunction(it))
    }
}

fun executeFunction(i: Int): String {

    val arr = executeList[i].arr

    var reverse = false
    executeList[i].func.forEach {
        when (it) {
            'D' -> {
                if (arr.isEmpty()) return "error"
                else {
                    if (reverse) arr.removeLast()
                    else arr.removeFirst()
                }
            }
            'R' -> reverse = !reverse
        }
    }

    if (reverse) arr.reverse()

    return arr.joinToString(",", "[", "]")
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    resultList.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
