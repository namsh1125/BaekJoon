import java.util.*

/*
* 백준 3040번. 백설 공주와 일곱 난쟁이
* https://www.acmicpc.net/problem/3040
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val arr = Array(size = 9) { 0 }.toMutableList()

    for(i in 0 until 9) {
        arr[i] = nextInt()
    }

    val sum = arr.sum()

    for(i in arr.indices) {
        for(j in i + 1 until arr.size) {

            if(sum - arr[i] - arr[j] == 100) {
                arr.removeAt(j)
                arr.removeAt(i)
                break
            }
        }
    }

    for(i in arr.indices) {
        println(arr[i])
    }
}
