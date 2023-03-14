/*
* 백준 2042번. 구간 합 구하기
* https://www.acmicpc.net/problem/2042
*/

data class Todo(val a: Int, val b: Int, val c: Long)

private var size = -1
private lateinit var num: LongArray
private lateinit var tree: LongArray

private val todoList = ArrayList<Todo>()

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val (n, m, k) = br.readLine().split(' ').map { it.toInt() }.also { size = it[0] }

    num = LongArray(n + 1) { 0L }
    repeat(n) { i ->
        num[i + 1] = br.readLine().toLong()
    }

    makeSegmentTree()

    repeat(m + k) {
        val (a, b, c) = br.readLine().split(' ').map { it.toLong() }
        todoList.add(Todo(a.toInt(), b.toInt(), c))
    }

    br.close()
}

private fun makeSegmentTree() {
    tree = LongArray(4 * size) { 0L }

    fun init(start: Int, end: Int, node: Int): Long {
        if (start == end) tree[node] = num[start]
        else {
            val mid = (start + end) / 2
            tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1)
        }

        return tree[node]
    }

    init(1, size, 1)
}

private fun getResult(): ArrayList<Long> {
    val result = ArrayList<Long>()

    for (todo in todoList) {
        when (todo.a) {
            1 -> {
                val diff = todo.c - num[todo.b]
                num[todo.b] = todo.c
                update(1, size, 1, todo.b, diff)
            }

            2 -> result.add(sum(1, size, 1, todo.b, todo.c.toInt()))
        }
    }

    return result
}

/**
 * start: 시작 index
 * end: 끝 index
 * index: 구간 합을 수정하고자 하는 노드
 * diff: 수정할 값
 */
private fun update(start: Int, end: Int, node: Int, index: Int, diff: Long) {
    if (index < start || index > end) return // 범위 밖인 경우

    tree[node] = tree[node] + diff

    if (start == end) return
    else {
        val mid = (start + end) / 2
        update(start, mid, node * 2, index, diff)
        update(mid + 1, end, node * 2 + 1, index, diff)
    }
}

/**
 * start: 시작 index
 * end: 끝 index
 * left .. right: 구간 합을 구하고자 하는 범위
 */
private fun sum(start: Int, end: Int, node: Int, left: Int, right: Int): Long {
    return if (left > end || right < start) 0L // 범위 밖인 경우
    else if (left <= start && end <= right) tree[node] // 범위 안인 경우
    else {
        val mid = (start + end) / 2
        sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right)
    }
}

private fun printResult(result: ArrayList<Long>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
