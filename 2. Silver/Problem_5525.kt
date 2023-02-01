/*
* 백준 5525번. IOIOI
* https://www.acmicpc.net/problem/5525
*/

private var ioiCount = -1
private var str = ""

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()

    ioiCount = br.readLine().toInt()

    br.readLine()
    str = br.readLine()

    br.close()
}

private fun getResult(): Int {

    var result = 0

    var i = 0
    var count = 0
    while (i < str.length - 2) {

        if (str[i] == 'I' && str[i + 1] == 'O' && str[i + 2] == 'I') {
            i += 2
            count++

            if (count == ioiCount) {
                result++
                count--
            }

        } else {
            i++
            count = 0
        }
    }

    return result
}

private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
