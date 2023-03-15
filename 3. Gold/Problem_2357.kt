import kotlin.math.max
import kotlin.math.min

/*
* 백준 2357번. 최솟값과 최댓값
* https://www.acmicpc.net/problem/2357
*/

data class Result(val min: Int, val max: Int)
data class Range(val start: Int, val end: Int)

private lateinit var minTree: IntArray
private lateinit var maxTree: IntArray
private lateinit var arr: IntArray
private var n = -1

private var rangeList = ArrayList<Range>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (_, m) = br.readLine().split(' ').map { it.toInt() }.also { n = it[0] }

    arr = IntArray(n + 1) { 0 }
    repeat(n) { i ->
        arr[i + 1] = br.readLine().toInt()
    }

    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        rangeList.add(Range(a, b))
    }

    br.close()
}

private fun makeSegmentTree() {
    minTree = IntArray(4 * n) { 0 }
    maxTree = IntArray(4 * n) { 0 }

    fun init(start: Int, end: Int, node: Int) {
        if (start == end) {
            minTree[node] = arr[start]
            maxTree[node] = arr[start]
        }

        else {
            val mid = (start + end) / 2
            init(start, mid, node * 2)
            init(mid + 1, end, node * 2 + 1)

            minTree[node] = min(minTree[node * 2], minTree[node * 2 + 1])
            maxTree[node] = max(maxTree[node * 2], maxTree[node * 2 + 1])
        }
    }

    init(1, n, 1)
}

private fun getResult(): ArrayList<Result> {
    makeSegmentTree()

    val resultList = ArrayList<Result>()
    for (range in rangeList) {
        val min = getMinValue(1, 1, n, range.start, range.end)
        val max = getMaxValue(1, 1, n, range.start, range.end)

        resultList.add(Result(min, max))
    }

    return resultList
}

/**
 * start: 시작 index
 * end: 끝 index
 * left .. right: 구간 합을 구하고자 하는 범위
 */
private fun getMinValue(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    return if (left > end || right < start) Int.MAX_VALUE
    else if (left <= start && end <= right) minTree[node]
    else {
        val mid = (start + end) / 2
        min(getMinValue(node * 2, start, mid, left, right), getMinValue(node * 2 + 1, mid + 1, end, left, right))
    }
}

/**
 * start: 시작 index
 * end: 끝 index
 * left .. right: 구간 합을 구하고자 하는 범위
 */
private fun getMaxValue(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    return if (left > end || right < start) Int.MIN_VALUE
    else if (left <= start && end <= right) maxTree[node]
    else {
        val mid = (start + end) / 2
        max(getMaxValue(node * 2, start, mid, left, right), getMaxValue(node * 2 + 1, mid + 1, end, left, right))
    }
}

private fun printResult(result: ArrayList<Result>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("${it.min} ${it.max}\n")
    }
    bw.flush()
    bw.close()
}
