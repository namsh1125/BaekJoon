import java.util.*
import kotlin.collections.ArrayList

/*
* 백준 1927번. 최소 힙
* https://www.acmicpc.net/problem/1927
*/

fun main() {

    val arr = initVariable()
    val result = getResult(arr)
    printResult(result)
}

fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val arr = arrayListOf<Int>()
    repeat(n) {
        arr.add(br.readLine().toInt())
    }

    return arr
}

fun getResult(arr: ArrayList<Int>): ArrayList<Int> {

    val result = arrayListOf<Int>()
    val queue = PriorityQueue<Int>()

    arr.forEach {
        if (it == 0) {
            if(queue.isEmpty()) result.add(0)
            else result.add(queue.remove())
        } else {
            queue.add(it)
        }
    }

    return result
}

fun printResult(result: ArrayList<Int>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
