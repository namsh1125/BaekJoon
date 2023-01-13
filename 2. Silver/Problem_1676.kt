/*
* 백준 1676번. 팩토리얼 0의 개수
* https://www.acmicpc.net/problem/1676
*/

fun main() {
    val num = initVariable()
    val result = getResult(num)
    printResult(result)
}

fun initVariable(): Int {
    val br = System.`in`.bufferedReader()
    return br.readLine().toInt()
}

fun getResult(num: Int): Int {
    var result = 0
    for (i in 0 .. num step 5) {
        if(i == 0) continue

        if (i % 125 == 0) result += 3
        else if (i % 25 == 0) result += 2
        else if (i % 5 == 0) result += 1
    }

    return result
}

fun printResult(result: Int) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
