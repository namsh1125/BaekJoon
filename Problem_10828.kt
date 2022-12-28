import java.util.*

/*
* 백준 10828번. 스택
* https://www.acmicpc.net/problem/10828
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    var n = nextLine().toInt()
    val stack = LinkedList<Int>()
    val result = arrayListOf<Int>()

    while (n-- > 0) {

        val line = nextLine().split(' ')

        when (line[0]) {

            "push" -> {
                stack.add(line[1].toInt())
            }

            "pop" -> {
                when (stack.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(stack.removeLast())
                }
            }

            "empty" -> {
                when (stack.isEmpty()) {
                    true -> result.add(1)
                    false -> result.add(0)
                }
            }

            "top" -> {
                when (stack.isEmpty()) {
                    true -> result.add(-1)
                    false -> result.add(stack.last)
                }

            }
            else -> result.add(stack.size)
        }
    }

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it" + "\n")
    }
    bw.flush()
    bw.close()
}
