import java.util.Scanner

/*
* 백준 1920번. 수 찾기
* https://www.acmicpc.net/problem/1920
*/

private val num = arrayListOf<Long>()
private var search = arrayListOf<Long>()
private var result = arrayListOf<Boolean>()

fun main() {

    initVariable()
    checkContain()
    printResult()
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()

    for (i in 0 until n) {
        num.add(nextLong())
    }

    val m = nextInt()
    for (i in 0 until m) {
        search.add(nextLong())
        result.add(false)
    }
}

fun checkContain() {

    num.sort()

    for (i in result.indices) {
        result[i] = binarySearch(search[i])
    }
}

fun binarySearch(find: Long): Boolean {

    var start = 0
    var end = num.size - 1

    while (start <= end) {

        val mid = (start + end) / 2

        if (find < num[mid]) end = mid - 1
        else if (find > num[mid]) start = mid + 1
        else return true
    }

    return false
}

fun printResult() {

    result.forEach {
        when (it) {
            true -> println("1")
            false -> println("0")
        }
    }
}
