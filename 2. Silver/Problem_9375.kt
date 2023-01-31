/*
* 백준 9375번. 패션왕 신해빈
* https://www.acmicpc.net/problem/9375
*/

private fun main() {

    val br = System.`in`.bufferedReader()
    val tc = br.readLine().toInt()
    val result = arrayListOf<Int>()

    repeat(tc) {
        val closet = HashMap<String, Int>()

        val n = br.readLine().toInt()
        repeat(n) {
            val (_, kind) = br.readLine().split(' ')

            if (closet.containsKey(kind)) {
                closet[kind] = closet[kind]!! + 1
            } else {
                closet[kind] = 1
            }
        }
        result.add(getResult(closet))
    }
    br.close()

    printResult(result)
}

private fun getResult(closet: HashMap<String, Int>): Int {

    var result = 1
    closet.forEach {
        result *= it.value + 1
    }

    return result - 1
}

private fun printResult(result: ArrayList<Int>) {
    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
