import java.util.StringTokenizer

/*
* 백준 11505번. 구간 곱 구하기
* https://www.acmicpc.net/problem/11505
*/

data class Todo(val a: Int, val b: Int, val c: Long)

private val todoList = ArrayList<Todo>()

private var size = -1
private lateinit var num: LongArray
private lateinit var tree: LongArray

private const val MOD = 1_000_000_007

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val (_, m, k) = br.readLine().split(' ').map { it.toInt() }.also { size = it[0] }

    num = LongArray(size + 1) { 0 }
    repeat(size) { i ->
        num[i + 1] = br.readLine().toLong()
    }

    repeat(m + k) {
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toLong()

        todoList.add(Todo(a, b, c))
    }

    br.close()
}

private fun getResult(): ArrayList<Long> {
    makeSegmentTree()

    val result = ArrayList<Long>()
    for (todo in todoList) {
        when (todo.a) {
            1 -> {
                num[todo.b] = todo.c
                update(1, todo.b, 1, size, todo.c)
            }

            2 -> result.add(mul(1, 1, size, todo.b, todo.c.toInt()))
        }
    }

    return result
}

private fun makeSegmentTree() {
    tree = LongArray(size * 4) { 0L }

    fun init(node: Int, start: Int, end: Int): Long {
        if (start == end) tree[node] = num[start]
        else {
            val mid = (start + end) / 2
            tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end) % MOD
        }

        return tree[node]
    }

    init(1, 1, size)
}

/**
 * node: 현재 위치
 * index: 바꾸고자 하는 위치
 * start: 시작 index
 * end: 끝 index
 * to: 수정할 값
 */
private fun update(node: Int, index: Int, start: Int, end: Int, to: Long): Long {
    when {
        index < start || index > end -> {}
        start == end -> tree[node] = to
        else -> {
            val mid = (start + end) / 2
            tree[node] = update(node * 2, index, start, mid, to) * update(node * 2 + 1, index, mid + 1, end, to) % MOD
        }
    }

    return tree[node]
}

/**
 * node: 현재 위치
 * start: 시작 index
 * end: 끝 index
 * left .. right: 구간 곱을 구하고자 하는 범위
 */
private fun mul(node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    return if (left > end || right < start) 1
    else if (left <= start && end <= right) tree[node]
    else {
        val mid = (start + end) / 2
        mul(node * 2, start, mid, left, right) * mul(node * 2 + 1, mid + 1, end, left, right) % MOD
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
