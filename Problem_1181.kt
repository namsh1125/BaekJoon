import java.util.Scanner

/*
* 백준 1181번. 단어 정렬
* https://www.acmicpc.net/problem/1181
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLine().toInt()
    var vocab = arrayListOf<String>()

    for (i in 0 until n) {
        vocab.add(nextLine())
    }

    val distinctVocab = vocab.distinct()
    val sort = distinctVocab.sortedWith(compareBy({ it.length }, { it }))

    sort.forEach {
        println(it)
    }

}

