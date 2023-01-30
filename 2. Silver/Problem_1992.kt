/*
* 백준 1992번. 쿼드트리
* https://www.acmicpc.net/problem/1992
*/

private lateinit var movie: Array<IntArray>
private var result = ""

private fun main() {
    initVariable()
    getResult(0, 0, movie.size)
    printResult()
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    movie = Array(size = n) { IntArray(size = n) { -1 } }
    for (i in movie.indices) {
        val line = br.readLine()
        for (j in movie[i].indices) {
            movie[i][j] = line[j] - '0'
        }
    }

    br.close()
}

private fun getResult(i: Int, j: Int, length: Int) {

    if(canCompress(i, j, length)) {
        result += movie[i][j]
    } else {
        result += "("

        getResult(i, j, length / 2)
        getResult(i, j + length / 2, length / 2)
        getResult(i + length / 2, j, length / 2)
        getResult(i + length / 2, j + length / 2, length / 2)

        result += ")"
    }
}

private fun canCompress(i: Int, j: Int, length: Int): Boolean {

    val value = movie[i][j]
    for(a in i until i + length) {
        for(b in j until j + length) {
            if(movie[a][b] != value) return false
        }
    }

    return true
}

private fun printResult() {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
