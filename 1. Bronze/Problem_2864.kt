/*
* 백준 2864번. 5와 6의 차이
* https://www.acmicpc.net/problem/2864
*/

private fun main() {
    val (a, b) = readln().split(' ')
    val min = a.replace('6', '5').toInt() + b.replace('6', '5').toInt()
    val max = a.replace('5', '6').toInt() + b.replace('5', '6').toInt()
    println("$min $max")
}
