/*
* 백준 2160번. 그림 비교
* https://www.acmicpc.net/problem/2160
*/

data class Diff(val picture1Num: Int, val picture2Num: Int, val diffCount: Int)

private lateinit var pictureList: Array<Array<Array<Char>>>

fun main() {

    val pictureList = initVariable()
    val result = getResult(pictureList)
    printResult(result)
}

fun initVariable(): Array<Array<Array<Char>>> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    pictureList = Array(size = n) { Array(size = 5) { Array(size = 7) { ' ' } } }

    for (i in pictureList.indices) {
        for (j in pictureList[i].indices) {

            val line = br.readLine()

            for (k in pictureList[i][j].indices) {
                pictureList[i][j][k] = line[k]
            }
        }
    }

    return pictureList
}

fun getResult(pictureList: Array<Array<Array<Char>>>): Diff {

    var result = Diff(0, 0, Int.MAX_VALUE)

    for (i in pictureList.indices) {
        for (j in i + 1 until pictureList.size) {

            var diff = 0
            for (a in 0 until 5) {
                for (b in 0 until 7) {
                    if (pictureList[i][a][b] != pictureList[j][a][b]) diff++
                }
            }

            if (result.diffCount > diff) {
                result = Diff(i + 1, j + 1, diff) // 그림이 1부터 시작하기 때문
            }
        }
    }

    return result
}

fun printResult(result: Diff) {

    val bw = System.out.bufferedWriter()
    bw.write("${result.picture1Num} ${result.picture2Num}\n")
    bw.flush()
    bw.close()
}
