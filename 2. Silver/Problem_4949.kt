/*
* 백준 4949번. 균형잡힌 세상
* https://www.acmicpc.net/problem/4949
*/

fun main() {

    val stringList = initVariable()
    val result = getResult(stringList)
    printResult(result)
}

fun initVariable(): ArrayList<String> {

    val br = System.`in`.bufferedReader()
    val stringList = ArrayList<String>()

    while (true) {

        val input = br.readLine()

        if (input == ".") break
        else stringList.add(input)
    }

    return stringList
}

fun getResult(stringList: ArrayList<String>): ArrayList<String> {

    val result = ArrayList<String>()

    stringList.forEach {
        result.add(getResult(it))
    }

    return result
}

fun getResult(str: String): String {

    val small = ArrayDeque<Int>() // 소괄호
    val big = ArrayDeque<Int>() // 대괄호

    for (i in str.indices) {

        if (str[i] == '(') small.add(i)
        else if (str[i] == ')') {
            if (small.isEmpty()) return "no"
            else if (big.isNotEmpty() && small.last() < big.last()) return "no"
            else small.removeLast()
        }

        else if (str[i] == '[') big.add(i)
        else if (str[i] == ']') {
            if (big.isEmpty()) return "no"
            else if (small.isNotEmpty() && small.last() > big.last()) return "no"
            else big.removeLast()
        }
    }

    return if (small.isNotEmpty() || big.isNotEmpty()) "no"
    else "yes"
}

fun printResult(result: ArrayList<String>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
