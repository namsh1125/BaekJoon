import java.util.*

/*
* 백준 18511번. 큰 수 구성하기
* https://www.acmicpc.net/problem/18511
*/

private var end = 0
private lateinit var num: ArrayList<Int>
private var answer = 0

fun main() {

    initVariable()
    getResult()

    val bw = System.out.bufferedWriter()
    bw.write("$answer\n")
    bw.close()
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, _) = nextLine().split(' ').map { it.toInt() }
    end = n
    num = nextLine().split(' ').map { it.toInt() } as ArrayList
}

fun getResult() {

    num.sort()
    dfs(0)
}


fun dfs(now: Int) {

    if (now > end) return
    if (answer < now) answer = now

    for (i in num.indices) {
        dfs(now * 10 + num[i])
    }

}
