import java.util.TreeMap

/*
* 백준 18870번. 좌표 압축
* https://www.acmicpc.net/problem/18870
*/

private fun main() {
    val arr = initVariable()
    val result = getResult(arr)
    printResult(result)
}

private fun initVariable(): List<Int> {
    val br = System.`in`.bufferedReader()
    br.readLine()

    val arr = br.readLine().split(' ').map { it.toInt() }

    br.close()
    return arr
}

private fun getResult(arr: List<Int>): IntArray {

    val ranking = arr.sorted().distinct()
    val map = TreeMap<Int, Int>()

    var i = 0
    ranking.forEach {
        if (!map.containsKey(it)) map[it] = i++
    }

    val result = IntArray(size = arr.size) { -1 }
    for (i in result.indices) {
        result[i] = map[arr[i]]!!
    }

    return result
}

private fun printResult(result: IntArray) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
