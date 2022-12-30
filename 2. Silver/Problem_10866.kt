import java.util.*
import kotlin.system.exitProcess

/*
* 백준 10866번. 덱
* https://www.acmicpc.net/problem/10866
*/

fun main() {

    val result = getResult()
    printResult(result)
}

fun getResult(): ArrayList<Int> = with(Scanner(System.`in`.bufferedReader())) {

    var n = nextLine().toInt()
    val deque = ArrayDeque<Int>()
    val result = arrayListOf<Int>()

    while (n-- > 0) {

        val line = nextLine().split(' ')

        when (line[0]) {

            "push_front" -> {
                deque.addFirst(line[1].toInt())
            }

            "push_back" -> {
                deque.addLast(line[1].toInt())
            }

            "pop_front" -> {
                when (deque.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(deque.removeFirst())
                }
            }

            "pop_back" -> {
                when (deque.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(deque.removeLast())
                }
            }

            "size" -> result.add(deque.size)

            "empty" -> {
                when (deque.isEmpty()) {
                    true -> result.add(1)
                    false -> result.add(0)
                }
            }

            "front" -> {
                when (deque.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(deque.first)
                }
            }

            "back" -> {
                when (deque.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(deque.last)
                }
            }

            else -> exitProcess(0)
        }
    }

    return result
}

fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
