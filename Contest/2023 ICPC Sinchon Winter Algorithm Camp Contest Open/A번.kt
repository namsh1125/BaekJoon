/*
* 2023 ICPC Sinchon Winter Algorithm Camp Contest Open A번 - 2023년은 검은 토끼의 해
* https://www.acmicpc.net/contest/problem/949/1
*/

private fun main() {
    var result = 0

    val n = readln().toInt()
    for (i in 2023 .. n) {
        var num = i.toString()

        var flag1 = false
        var flag2 = false
        var flag3 = false

        for (a in num.indices) {
            if (num[a] == '2') flag1 = true
            if (flag1 == true && num[a] == '0') flag2 = true
            if (flag2 == true && num[a] == '2') flag3 = true
            if (flag3 == true && num[a] == '3') {
                result++
                break
            }
        }
    }

    printResult(result)
}


private fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
