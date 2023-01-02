/*
* 백준 2805번. 나무 자르기
* https://www.acmicpc.net/problem/2805
*/

private lateinit var treeList: ArrayList<Int>
private var wantHeight = -1

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (_, m) = br.readLine().split(' ').map { it.toInt() }

    treeList = br.readLine().split(' ').map { it.toInt() } as ArrayList<Int>

    wantHeight = m
}

fun getResult(): Int {

    var start = 1
    var end = 1_000_000_000

    while (start < end) {

        val mid = (start + end) / 2
        var height = 0L

        treeList.forEach() {
            if (mid < it) {
                height += it - mid
            }
        }

        if (height < wantHeight) end = mid
        else start = mid + 1
    }

    return start - 1
}

fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
