/*
* 백준 1874번. 스택 수열
* https://www.acmicpc.net/problem/1874
*/

private

fun main() {

    val num = initVariable()
    val result = getResult(num)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val num = arrayListOf<Int>()

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    for (i in 0 until n) {
        num.add(br.readLine().toInt())
    }

    return num
}

fun getResult(arr: ArrayList<Int>): ArrayList<Char> {

    val stack = ArrayDeque<Int>()
    val result = ArrayList<Char>()

    var now = 1
    while (arr.isNotEmpty()) {

        val num = arr.removeFirst()
        while (now <= num) {
            stack.add(now++)
            result.add('+')
        }

        if (stack.last() == num) {
            stack.removeLast()
            result.add('-')
        } else {
            return arrayListOf()
        }
    }

    return result
}

fun printResult(result: ArrayList<Char>) {

    val bw = System.out.bufferedWriter()
    when (result.size) {
        0 -> bw.write("NO")
        else -> {
            result.forEach {
                bw.write("$it\n")
            }
        }
    }
    bw.flush()
    bw.close()
}
