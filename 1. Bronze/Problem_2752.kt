/*
* 백준 2752번. 세수정렬
* https://www.acmicpc.net/problem/2752
*/

private fun main() {
    var num = readln().split(' ').map { it.toInt() }
    num = num.sorted()
    num.forEach {
        print("$it ")
    }
}
