import java.util.*

/*
* 백준 14248번. 점프 점프
* https://www.acmicpc.net/problem/14248
*/

private var bridge: ArrayList<Int> = arrayListOf()
private var visited: ArrayList<Boolean> = arrayListOf()

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    // 변수 초기화
    val n = readLine()!!.toInt()
    val st = StringTokenizer(readLine())

    while(st.hasMoreTokens()){
        bridge.add(st.nextToken().toInt())
        visited.add(false)
    }

    val position = readLine()!!.toInt()

    // DFS algorithm
    dfs(position - 1) // 배열은 0부터 시작하기 때문

    // 결과 출력
    printResult()
}

fun dfs(position: Int) {
    if(position < 0 || position >= visited.size) // 범위 밖인 경우
        return

    visited[position] = true

    dfs(position + bridge[position])
    dfs(position - bridge[position])

}

fun printResult() {

    var result = 0

    for(i in visited.indices) {
        if(visited[i]) {
            result++
        }
    }

    println("$result")
}
