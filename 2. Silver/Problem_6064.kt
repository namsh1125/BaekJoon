/*
* 백준 6064번. 카잉 달력
* https://www.acmicpc.net/problem/6064
*/

private fun main() {

    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()
    val result = ArrayList<Long>()

    repeat(tc) {
        val (m, n, x, y) = br.readLine().split(' ').map { it.toLong() }
        result.add(getResult(m, n, x, y))
    }
    br.close()

    printResult(result)
}

private fun getResult(m: Long, n: Long, x: Long, y: Long): Long {

    val lcm = m * n / gcd(m, n)

    for (i in x..lcm step m) {
        val cmp = if (i % n == 0L) n else i % n
        if (cmp == y) return i
    }

    return -1
}

private fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a
    else gcd(b, a % b)
}

private fun printResult(result: ArrayList<Long>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
