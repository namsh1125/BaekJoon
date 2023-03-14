/*
* 백준 10872번. 팩토리얼
* https://www.acmicpc.net/problem/10872
*/

private fun main() {
    val n = readln().toInt()
    val result = getResult(n)
    printResult(result)
}

private fun getResult(n: Int): Int {
    return if (n == 0) 1
    else {
        var result = 1
        for (i in 1..n) {
            result *= i
        }

        result
    }
}

private fun printResult(result: Int) {
    println(result)
}
