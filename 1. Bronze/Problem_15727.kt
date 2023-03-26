/*
* 백준 15727번. 조별과제를 하려는데 조장이 사라졌다
* https://www.acmicpc.net/problem/15727
*/

private fun main() {
    val n = readln().toInt()
    val result = if (n % 5 == 0) n / 5 else n / 5 + 1
    println(result)
}
