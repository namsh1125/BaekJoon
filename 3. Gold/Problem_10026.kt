/*
* 백준 10026번. 적록색약
* https://www.acmicpc.net/problem/10026
*/

private lateinit var picture: Array<CharArray>
private lateinit var colorWeaknessPicture: Array<CharArray>

private val di = arrayOf(1, -1, 0, 0)
private val dj = arrayOf(0, 0, 1, -1)

private fun main() {
    
    val picture = initVariable()
    val nonWeakness = getResult(picture)
    val weakness = getResult(colorWeaknessPicture)
    
    printResult(nonWeakness, weakness)
}

private fun initVariable(): Array<CharArray> {

    val n = readln().toInt()
    picture = Array(size = n) { CharArray(size = n) { ' ' } }
    colorWeaknessPicture = Array(size = n) { CharArray(size = n) { ' ' } }

    for (i in picture.indices) {
        val line = readln()
        for (j in picture[i].indices) {
            picture[i][j] = line[j]
            colorWeaknessPicture[i][j] = if (line[j] == 'G') 'R' else line[j]
        }
    }

    return picture
}

private fun getResult(picture: Array<CharArray>): Int {

    var result = 0
    val visited = Array(size = picture.size) { BooleanArray(size = picture.first().size) { false } }

    for (i in picture.indices) {
        for (j in picture[i].indices) {
            if (!visited[i][j]) {
                visited[i][j] = true
                dfs(i, j, picture, visited)
                result++
            }
        }
    }

    return result
}

fun dfs(i: Int, j: Int, picture: Array<CharArray>, visited: Array<BooleanArray>) {

    for (k in 0 until 4) {

        val ni = i + di[k]
        val nj = j + dj[k]

        if (isInRange(ni, nj) && !visited[ni][nj] && picture[i][j] == picture[ni][nj]) {
            visited[ni][nj] = true
            dfs(ni, nj, picture, visited)
        }
    }

}

private fun isInRange(i: Int, j: Int): Boolean {
    return i in picture.indices && j in picture[i].indices
}

private fun printResult(nonWeakness: Int, weakness: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$nonWeakness $weakness\n")
    bw.flush()
    bw.close()
}
