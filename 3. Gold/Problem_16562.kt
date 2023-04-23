import java.util.PriorityQueue
import java.util.StringTokenizer

/*
* 백준 16562번. 친구비
* https://www.acmicpc.net/problem/16562
*/

data class Friend(
    val num: Int,
    val money: Int
) : Comparable<Friend> {
    override fun compareTo(other: Friend): Int {
        return money - other.money
    }
}

private var n = -1 // 학생 수
private var m = -1 // 친구 관계 수
private var k = -1 // 가지고 있는 돈

private val friendList = PriorityQueue<Friend>()
private lateinit var parent: IntArray

private const val ME = 0

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(' ').map { it.toInt() }.also {
        n = it[0]
        m = it[1]
        k = it[2]
    }

    parent = IntArray(n + 1) { it }

    val st = StringTokenizer(br.readLine())
    repeat(n) { i ->
        val money = st.nextToken().toInt()
        friendList.add(Friend(i + 1, money))
    }

    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        unionParent(a, b)
    }

    br.close()
}

private fun unionParent(i: Int, j: Int) {
    val parI = findParent(i)
    val parJ = findParent(j)

    if (parI > parJ) parent[parI] = parJ
    else parent[parJ] = parI
}

private fun findParent(i: Int): Int {
    return if (i == parent[i]) i
    else {
        parent[i] = findParent(parent[i])
        parent[i]
    }
}

private fun getResult(): String {
    var result = 0 // 친구로 만드는데 드는 최소 비용

    while (friendList.isNotEmpty()) {
        val cur = friendList.remove()

        if (findParent(cur.num) != ME) { // 나랑 친구가 아니라면
            result += cur.money // 친구비 입금하고
            unionParent(cur.num, ME) // 친구하기
        }
    }

    return if (result > k) "Oh no" else "$result"
}

private fun printResult(result: String) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
