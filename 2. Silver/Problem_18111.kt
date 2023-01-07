import java.util.StringTokenizer

/*
* 백준 18111번. 마인크래프트
* https://www.acmicpc.net/problem/18111
*/

data class Result(val height: Int, val time: Int)

private lateinit var ground: Array<IntArray>
private var bag = -1

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()

    val (n, m, b) = br.readLine().split(' ').map { it.toInt() }

    ground = Array(size = n) { IntArray(size = m) { -1 } }
    for (i in 0 until n) {

        val st = StringTokenizer(br.readLine())
        for (j in 0 until m) {
            ground[i][j] = st.nextToken().toInt()
        }
    }

    bag = b
}

fun getResult(): Result {

    var result = Result(-1, Int.MAX_VALUE)
    for (height in 0..256) {

        var build = 0
        var remove = 0

        for (i in ground.indices) {
            for (j in ground[i].indices) {
                if (ground[i][j] > height) remove += ground[i][j] - height
                if (ground[i][j] < height) build += height - ground[i][j]
            }
        }

        if (remove + bag >= build) {
            val time = remove * 2 + build
            if (time <= result.time) {
                result = Result(height, time)
            }
        }
    }

    return result
}

fun printResult(result: Result) {

    val bw = System.out.bufferedWriter()
    bw.write("${result.time} ${result.height}\n")
    bw.flush()
    bw.close()
}
