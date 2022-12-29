import java.util.*
import kotlin.collections.ArrayList

/*
* 백준 10816번. 숫자 카드 2
* https://www.acmicpc.net/problem/10816
*/

private var cardInfo = HashMap<Int, Int>()
private lateinit var find: ArrayList<Int>
private var result = arrayListOf<Int>()

fun main() {

    initVariable()
    solveProblem()
    printResult()
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLine().toInt()
    val card = nextLine().split(' ').map { it.toInt() } as ArrayList

    for(i in card.indices) {
        if(cardInfo.containsKey(card[i])) {
            cardInfo[card[i]] = cardInfo[card[i]]!! + 1
        } else {
            cardInfo[card[i]] = 1
        }
    }

    val m = nextLine().toInt()
    find = nextLine().split(' ').map { it.toInt() } as ArrayList
}

fun solveProblem() {

    find.forEach {
        if(cardInfo.containsKey(it)) result.add(cardInfo[it]!!)
        else result.add(0)
    }
}

fun printResult() {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("${it} ")
    }
    bw.flush()
    bw.close()
}
