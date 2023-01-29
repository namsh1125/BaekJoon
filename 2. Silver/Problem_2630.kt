import java.util.StringTokenizer

/*
* 백준 2630번. 색종이 만들기
* https://www.acmicpc.net/problem/2630
*/

private lateinit var paper: Array<IntArray>

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    paper = Array(size = n) { IntArray(size = n) { -1 } }
    for (i in paper.indices) {
        val st = StringTokenizer(br.readLine())
        for (j in paper[i].indices) {
            paper[i][j] = st.nextToken().toInt()
        }
    }

    br.close()
}

private fun getResult(): IntArray {
    val count = IntArray(size = 2) { 0 } // white, color
    getResult(0, 0, paper.size, count)
    return count
}

private fun getResult(i: Int, j: Int, length: Int, count: IntArray) {

    if (haveToCut(i, j, length)) {
        getResult(i, j, length / 2, count)
        getResult(i + length / 2, j, length / 2, count)
        getResult(i, j + length / 2, length / 2, count)
        getResult(i + length / 2, j + length / 2, length / 2, count)
    } else {
        count[paper[i][j]]++
    }
}

private fun haveToCut(i: Int, j: Int, length: Int): Boolean {

    val color = paper[i][j]
    for (a in i until i + length) {
        for (b in j until j + length) {
            if (paper[a][b] != color) return true
        }
    }

    return false
}

private fun printResult(result: IntArray) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
