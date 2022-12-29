import java.util.*

/*
* 백준 1259번. 팰린드롬수
* https://www.acmicpc.net/problem/1259
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val resultList = arrayListOf<Boolean>()

    while (true) {

        val line = nextLine().toCharArray()
        if (line[0] == '0')
            break

        var result = false
        for (i in 0..line.size / 2) {

            if (line[i] != line[line.size - 1 - i]) break
            if (line[i] == line[line.size - 1 - i] && i == line.size / 2) result = true
        }

        resultList.add(result)
    }


    val bw = System.out.bufferedWriter()
    resultList.forEach {
        when(it) {
            true -> bw.write("yes\n")
            false -> bw.write("no\n")
        }
    }
    bw.flush()
    bw.close()
}
