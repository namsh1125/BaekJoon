/*
* 백준 14428번. 수열과 쿼리 16
* https://www.acmicpc.net/problem/14428
*/

data class Query(val perform: Int, val i: Int, val j: Int)

private val queryList = ArrayList<Query>()

private var size = -1
private lateinit var arr: IntArray
private lateinit var tree: IntArray

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    size = br.readLine().toInt()
    arr = IntArray(size + 1) { Int.MAX_VALUE }
    br.readLine().split(' ').forEachIndexed { index, it ->
        arr[index + 1] = it.toInt()
    }

    val m = br.readLine().toInt()
    repeat(m) {
        val (p, i, j) = br.readLine().split(' ').map { it.toInt() }
        queryList.add(Query(p, i, j))
    }

    br.close()
}

private fun getResult(): ArrayList<Int> {
    makeSegmentTree()

    val result = arrayListOf<Int>()
    for (query in queryList) {
        when (query.perform) {
            1 -> {
                arr[query.i] = query.j
                update(1, query.i, 1, size)
            }

            2 -> result.add(min(1, 1, size, query.i, query.j))
        }
    }

    return result
}

private fun makeSegmentTree() {
    tree = IntArray(size * 4) { Int.MAX_VALUE }

    fun init(index: Int, start: Int, end: Int): Int {
        if (start == end) tree[index] = start
        else {
            val mid = (start + end) / 2
            val left = init(index * 2, start, mid)
            val right = init(index * 2 + 1, mid + 1, end)

            tree[index] = if (arr[left] <= arr[right]) left else right
        }

        return tree[index]
    }

    init(1, 1, size)
}

/**
 * node: 현재 위치
 * index: 바꾸고자 하는 위치
 * start: 시작 index
 * end: 끝 index
 */
private fun update(node: Int, index: Int, start: Int, end: Int) {
    when {
        index < start || index > end -> return
        start == end -> return
        else -> {
            val mid = (start + end) / 2
            update(node * 2, index, start, mid)
            update(node * 2 + 1, index, mid + 1, end)

            val left = tree[node * 2]
            val right = tree[node * 2 + 1]

            tree[node] = if (arr[left] <= arr[right]) left else right
        }
    }
}

/**
 * node: 현재 위치
 * start: 시작 index
 * end: 끝 index
 * left .. right: 최솟값을 구하고자 하는 범위
 */
private fun min(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    return if (left > end || right < start) 0
    else if (left <= start && end <= right) tree[node]
    else {
        val mid = (start + end) / 2
        val leftIndex = min(node * 2, start, mid, left, right)
        val rightIndex = min(node * 2 + 1, mid + 1, end, left, right)

        if (arr[leftIndex] <= arr[rightIndex]) leftIndex else rightIndex
    }
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
