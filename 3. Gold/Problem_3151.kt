/*
* 백준 3151번. 합이 0
* https://www.acmicpc.net/problem/3151
*/

private var n = -1
private lateinit var student: List<Long>

fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    student = br.readLine().split(' ').map { it.toLong() }
    br.close()
}

private fun getResult(): Long {
    student = student.sorted()

    var result = 0L
    for (i in 0 until n - 2) {
        var leftPtr = i + 1
        var rightPtr = n - 1

        while (leftPtr < rightPtr) {
            val sum = student[i] + student[leftPtr] + student[rightPtr]

            when {
                sum == 0L -> {
                    if (student[leftPtr] == student[rightPtr]) {
                        val n = rightPtr - leftPtr + 1
                        result += n * (n - 1) / 2
                        break
                    }

                    else {
                        var leftCnt = 0
                        var rightCnt = 0

                        var num = student[leftPtr]
                        while (student[leftPtr] == num) {
                            leftCnt++
                            leftPtr++
                        }

                        num = student[rightPtr]
                        while (student[rightPtr] == num) {
                            rightCnt++
                            rightPtr--
                        }

                        result += leftCnt * rightCnt
                    }
                }

                sum < 0 -> leftPtr++
                sum > 0 -> rightPtr--
            }
        }
    }

    return result
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result")
    bw.flush()
    bw.close()
}
