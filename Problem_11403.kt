import java.util.Scanner

/*
* 백준 11403번. 경로 찾기
* https://www.acmicpc.net/problem/11403
*/

private lateinit var graph: Array<Array<Int>>

fun main() {

    initVariable()
    solveProblem()
    printResult()
}

fun printResult() {

    for(i in graph.indices){
        for(j in graph.indices) {
            print("${graph[i][j]} ")
        }
        println()
    }
}

fun solveProblem() {

    for(k in graph.indices) {
        for(i in graph.indices) {
            for(j in graph.indices) {
                if(graph[i][k] == 1 && graph[k][j] == 1) {
                    graph[i][j] = 1
                }
            }
        }
    }
}

fun initVariable() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextInt()
    graph = Array(size = n) { Array(size = n) { 0 } }

    for(i in graph.indices) {
        for(j in graph.indices) {
            graph[i][j] = nextInt()
        }
    }

}
