import kotlin.math.min

/*
* 백준 27505번. 천국의 계단
* https://www.acmicpc.net/problem/27505
*/

private var n = -1L
private var block1 = -1L
private var block2 = -1L

private fun main() {
    initVariable()
    val result = getResult()
    printResult(result)
}

private fun initVariable() {
    readln().split(' ').map { it.toLong() }.also {
        n = it[0]
        block1 = it[1]
        block2 = it[2]
    }
}

private fun getResult(): Long {
    if (n < min(block1, block2)) return n

    var canMake = 0L
    val lcm = lcm(block1, block2)

    for (i in 0 until lcm / block2) {
        val length = n - block2 * i // block2를 i개만큼 쌓음

        if (length < 0) break
        else canMake += 1 + length / block1 // block1을 length 이내로 쌓기
    }
    canMake-- // block1과 block2를 하나도 안 쌓은 경우 제거

    return n - canMake
}

private fun lcm(a: Long, b: Long): Long {
    return a * b / gcd(a, b)
}

private fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) return a
    else gcd(b, a % b)
}

private fun printResult(result: Long) {
    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}
