import java.util.*

/*
* 백준 1371번. 가장 많은 글자
* https://www.acmicpc.net/problem/1371
*/

private fun main() = with(Scanner(System.`in`)) {
    val num = IntArray(26) { 0 }

    while (hasNextLine()) {
        val line = nextLine()

        line.forEach {
            if (it in 'a'..'z') num[it - 'a']++
        }
    }

    val max = num.max()

    var result = ""
    for (i in 0 until 26) {
        if (num[i] == max) result += 'a' + i
    }

    println(result)
}
