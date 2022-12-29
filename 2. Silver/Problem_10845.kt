import java.util.*

/*
* 백준 10845번. 큐
* https://www.acmicpc.net/problem/10845
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    var n = nextLine().toInt()
    val queue = LinkedList<Int>()
    val result = arrayListOf<Int>()

    while (n-- > 0) {

        val line = nextLine().split(' ')

        when (line[0]) {

            "push" -> {
                queue.add(line[1].toInt())
            }

            "pop" -> {
                when (queue.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(queue.poll())
                }
            }

            "empty" -> {
                when (queue.isEmpty()) {
                    true -> result.add(1)
                    false -> result.add(0)
                }
            }

            "front" -> {
                when (queue.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(queue.peek())
                }
            }

            "back" -> {
                when (queue.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(queue.last)
                }

            }
            else -> result.add(queue.size)
        }
    }

    val bufferedWriter = System.out.bufferedWriter()
    result.forEach {
        bufferedWriter.write("$it" + "\n")
    }
    bufferedWriter.flush()
    bufferedWriter.close()
}
