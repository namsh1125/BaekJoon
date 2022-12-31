import java.lang.Integer.min

/*
* 백준 18243번. Small World Network
* https://www.acmicpc.net/problem/18243
*/

private lateinit var network: Array<IntArray>
private const val max = 9999999

fun main() {

    initVariable()
    fillNetwork()
    val result = checkRelation()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine()!!.split(' ').map { it.toInt() }
    network = Array(size = n) { IntArray(size = n) { max } }

    for (i in 0 until k) {

        val line = br.readLine()!!.split(' ').map { it.toInt() - 1 }
        network[line[0]][line[1]] = 1
        network[line[1]][line[0]] = 1
    }
}

fun fillNetwork() {

    for (i in network.indices) {
        for (j in network.indices) {

            if (i == j) continue
            for (k in network.indices) {

                if (i == k || j == k) continue
                network[j][k] = min(network[j][k], network[j][i] + network[i][k])
            }
        }
    }
}

fun checkRelation(): Boolean {

    for (i in network.indices) {
        for (j in network[i].indices) {

            if (i == j) continue

            if (network[i][j] > 6)
                return false
        }
    }

    return true
}

fun printResult(result: Boolean) {

    val bw = System.out.bufferedWriter()
    when (result) {
        true -> bw.write("Small World!\n")
        false -> bw.write("Big World!\n")
    }
    bw.flush()
    bw.close()
}
