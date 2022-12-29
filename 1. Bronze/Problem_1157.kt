import java.util.Scanner

/*
* 백준 1157번. 단어 공부
* https://www.acmicpc.net/problem/1157
*/

val num = Array(26) { 0 }

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    countAlphabet()

    // Print result
    when (val result = getResult()) {
        -1 -> println("?")
        else -> println('A' + result)
    }

}

fun getResult(): Int {

    val max = num.maxOrNull()
    var position = -1

    for (i in num.indices) {

        if (num[i] == max && position != -1) {
            return -1
        }

        if (num[i] == max && position == -1) {
            position = i
        }

    }


    return position
}

fun countAlphabet() {

    val word = readLine()!!.lowercase()

    for (i in word.indices) {
        num[word[i] - 'a']++
    }
}
