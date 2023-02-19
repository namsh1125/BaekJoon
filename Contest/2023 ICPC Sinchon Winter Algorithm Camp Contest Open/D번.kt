/*
* 2023 ICPC Sinchon Winter Algorithm Camp Contest Open D번 - 알파벳 블록
* https://www.acmicpc.net/contest/problem/949/4
*/

private val result = ArrayDeque<Char>()

private fun main() {
    val order = ArrayDeque<Int>()
    var i = 0

    val n = readln().toInt()
    repeat(n) {
        val line = readln().split(' ')
        when (line[0]) {
            "1" -> {
                result.addLast(line[1][0])
                order.addLast(++i)
            }

            "2" -> {
                result.addFirst(line[1][0])
                order.addFirst(++i)
            }

            "3" -> {
                if (result.isNotEmpty()) {
                    var index = 0
                    for (k in order.indices) {
                        if (i == order[k]) {
                            index = k
                            i--
                            break
                        }
                    }

                    result.removeAt(index)
                    order.removeAt(index)
                }
            }
        }
    }

    printResult()
}


private fun printResult() {
    val bw = System.out.bufferedWriter()

    if (result.isEmpty()) bw.write("0\n")
    else {
        result.forEach {
            bw.write("$it")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}
