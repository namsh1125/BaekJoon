/*
* 백준 1264번. 모음의 개수
* https://www.acmicpc.net/problem/1264
*/

private fun main() {
    while (true) {
        val line = readln()
        if (line == "#") break

        var result = 0
        for (i in line.indices) {
            when (line[i]) {
                'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' -> result++
            }
        }

        println(result)
    }
}
