/*
* 백준 1003번. 피보나치 함수
* https://www.acmicpc.net/problem/1003
*/

data class Result(val zero: Int, val one: Int)

private val testCase = arrayListOf<Int>()

fun main() {

    initVariable()
    val result = getResult()
    printResult(result)
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    for (i in 0 until t) {
        testCase.add(br.readLine().toInt())
    }
}

fun getResult(): ArrayList<Result> {

    val resultList = arrayListOf<Result>()

    val fibo = Array(size = 41) { 0 }
    fibo[0] = 0
    fibo[1] = 1

    for (i in 2..40) {
        fibo[i] = fibo[i - 1] + fibo[i - 2]
    }

    for(i in testCase.indices) {

        if(testCase[i] == 0) resultList.add(Result(1, 0))
        else resultList.add(Result(fibo[testCase[i] - 1], fibo[testCase[i]]))
    }

    return resultList
}

fun printResult(result: ArrayList<Result>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("${it.zero} ${it.one}\n")
    }
    bw.flush()
    bw.close()
}
