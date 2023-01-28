import java.util.StringTokenizer

/*
* 백준 1043번. 거짓말
* https://www.acmicpc.net/problem/1043
*/

private val know = ArrayList<Int>()
private lateinit var parent: IntArray
private lateinit var partyList: Array<ArrayList<Int>>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    parent = IntArray(size = n + 1) { it }

    val st = StringTokenizer(br.readLine())
    repeat(st.nextToken().toInt()) {
        know.add(st.nextToken().toInt())
    }

    partyList = Array(size = m) { arrayListOf() }
    repeat(m) { i ->
        val st = StringTokenizer(br.readLine())
        repeat(st.nextToken().toInt()) {
            partyList[i].add(st.nextToken().toInt())
        }
    }
}

private fun getResult(): Int {

    for (party in partyList) {
        for (person in party) {
            unionParent(party[0], person, parent) // 해당 파티에 참석한 사람들끼리 union
        }
    }

    var result = 0
    for (party in partyList) {

        var lie = true
        for (person in party) {
            for (knowTruth in know) {
                if (findParent(person, parent) == findParent(knowTruth, parent)) {
                    lie = false
                    break
                }
            }
        }

        if (lie) result++
    }

    return result
}

private fun findParent(i: Int, parent: IntArray): Int {
    return if (parent[i] == i) i
    else findParent(parent[i], parent)
}

private fun unionParent(i: Int, j: Int, parent: IntArray) {
    val parent1 = findParent(i, parent)
    val parent2 = findParent(j, parent)

    if (parent1 < parent2) parent[parent2] = parent1
    else parent[parent1] = parent2
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
