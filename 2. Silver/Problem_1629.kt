/*
* 백준 1629번. 곱셈
* https://www.acmicpc.net/problem/1629
*/

fun main() {

    val br = System.`in`.bufferedReader()
    val (a, b, c) = br.readLine().split(' ').map { it.toLong() }

    val result = getMod(a, b, c)

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.close()
}

private fun getMod(base: Long, exponent: Long, mod: Long): Long {

    if (exponent == 1L) return base % mod

    val halfExponentMod = getMod(base, exponent / 2, mod)

    return if (exponent % 2 == 1L) {
        (halfExponentMod * halfExponentMod % mod) * base % mod
    } else {
        halfExponentMod * halfExponentMod % mod
    }
}
