import java.util.StringTokenizer

/*
* 백준 1780번. 종이의 개수
* https://www.acmicpc.net/problem/1780
*/

private lateinit var paper: Array<IntArray>
private val count = IntArray(3) { 0 }

private fun main() {
    initVariable()
    getResult(0, 0, paper.size)
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    paper = Array(n) { IntArray(n) { 0 } }

    for (i in paper.indices) {

        val st = StringTokenizer(br.readLine())
        for (j in paper[i].indices) {
            paper[i][j] = st.nextToken().toInt()
        }
    }

    br.close()
}

private fun getResult(i: Int, j: Int, length: Int) {

    if (haveToCut(i, j, length)) {
        val cutLength = length / 3
        
        getResult(i, j, cutLength)
        getResult(i, j + cutLength, cutLength)
        getResult(i, j + cutLength * 2, cutLength)

        getResult(i + cutLength, j, cutLength)
        getResult(i + cutLength, j + cutLength, cutLength)
        getResult(i + cutLength, j + cutLength * 2, cutLength)

        getResult(i + cutLength * 2, j, cutLength)
        getResult(i + cutLength * 2, j + cutLength, cutLength)
        getResult(i + cutLength * 2, j + cutLength * 2, cutLength)

    } else {
        count[paper[i][j] + 1]++
    }
}

private fun haveToCut(i: Int, j: Int, length: Int): Boolean {

    for (a in i until i + length) {
        for (b in j until j + length) {
            if (paper[a][b] != paper[i][j]) return true
        }
    }

    return false
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    count.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
