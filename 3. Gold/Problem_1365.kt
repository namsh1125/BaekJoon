/*
* 백준 1365번. 꼬인 전깃줄
* https://www.acmicpc.net/problem/1365
*/

private fun main() {

    val arr = initVariable()
    val result = getResult(arr)
    printResult(result)
}

private fun initVariable(): List<Int> {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    return br.readLine().split(' ').map { it.toInt() }
}

private fun getResult(arr: List<Int>): Int {

    val lcs = arrayListOf(arr[0])
    for(i in 1 until arr.size) {

        if(lcs.last() < arr[i]) {
            lcs.add(arr[i])
        }

        else {
            var start = 0
            var end = lcs.size

            while(start < end) {

                val mid = (start + end) / 2

                if(lcs[mid] < arr[i]) start = mid + 1
                else end = mid
            }

            lcs[start] = arr[i]
        }
    }

    return arr.size - lcs.size
}

private fun printResult(result: Int) {

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
