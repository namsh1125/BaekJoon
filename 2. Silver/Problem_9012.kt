import java.util.*

/*
* 백준 9012번. 괄호
* https://www.acmicpc.net/problem/9012
*/

fun main() = with(Scanner(System.`in`.bufferedReader())){

    val t = nextLine().toInt()
    val result = arrayListOf<Boolean>()

    for(i in 0 until t) {

        val input = nextLine()
        result.add(checkVPS(input))
    }

    val bw = System.out.bufferedWriter()
    result.forEach {
        when(it) {
            true -> bw.write("YES\n")
            false -> bw.write("NO\n")
        }
    }
    bw.flush()
    bw.close()
}

fun checkVPS(line: String): Boolean {

    val stack = Stack<Char>()

    for(i in line.indices) {

        if(line[i] == '(') stack.push('(')
        else {
            if(stack.empty()) return false
            else stack.pop()
        }
    }

    return stack.empty()
}
