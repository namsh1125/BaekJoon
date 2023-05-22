import kotlin.math.ceil
import kotlin.math.max

/*
* 백준 5532번. 방학 숙제
* https://www.acmicpc.net/problem/5532
*/

private fun main() {
    val l = readln().toInt()
    val a = readln().toDouble()
    val b = readln().toDouble()
    val c = readln().toDouble()
    val d = readln().toDouble()

    val solve = max(ceil(a / c), ceil(b / d)).toInt()

    println(l - solve)
}
