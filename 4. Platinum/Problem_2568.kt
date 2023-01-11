import java.util.TreeMap

/*
* 백준 2568번. 전깃줄 - 2
* https://www.acmicpc.net/problem/2568
*/

private val connectA = TreeMap<Int, Int>()
private val connectB = TreeMap<Int, Int>()

private fun main() {

    val arr = initVariable()
    val result = getResult(arr)
    printResult(result)
}

private fun initVariable(): ArrayList<Int> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val arr = arrayListOf<Int>()

    repeat(n) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        connectA[a] = b
        connectB[b] = a
    }

    connectA.forEach {
        arr.add(it.value)
    }

    return arr
}

private fun getResult(arr: ArrayList<Int>): List<Int> {

    val dp = getLcsLength(arr)
    val removeList = getRemoveList(arr, dp)
    val result = getPositionList(removeList)

    return result
}

private fun getLcsLength(arr: ArrayList<Int>): IntArray {

    val dp = IntArray(size = arr.size) { 1 }

    val lcs = arrayListOf(arr[0])
    for (i in 1 until arr.size) {

        if (lcs.last() < arr[i]) {
            dp[i] = lcs.size + 1
            lcs.add(arr[i])
        }

        else {
            var start = 0
            var end = lcs.size

            while (start < end) {
                val mid = (start + end) / 2

                if (lcs[mid] < arr[i]) start = mid + 1
                else end = mid
            }

            lcs[start] = arr[i]
            dp[i] = start + 1
        }
    }

    return dp
}

private fun getRemoveList(arr: ArrayList<Int>, dp: IntArray): ArrayList<Int> {

    val remove = ArrayList<Int>()
    var length = dp.max()
    for (i in dp.size - 1 downTo 0) {
        if (length == dp[i]) {
            length--
        } else {
            remove.add(arr[i])
        }
    }

    return remove
}

private fun getPositionList(remove: ArrayList<Int>): List<Int> {

    val result = arrayListOf<Int>()
    remove.forEach {
        result.add(connectB[it]!!)
    }

    return result.reversed()
}

private fun printResult(result: List<Int>) {

    val bw = System.out.bufferedWriter()
    bw.write("${result.size}\n")
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
