import java.util.Scanner

/*
* 백준 15724번. 주지수
* https://www.acmicpc.net/problem/15724
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, m) = nextLine().split(' ').map { it.toInt() }
    val territory = Array(size = n) { Array(size = m) { 0 } }
    val prefixSum = Array(size = n + 1) { Array(size = m + 1) { 0 } }

    // 영토 입력받기
    for (i in 0 until n) {
        for (j in 0 until m) {
            territory[i][j] = nextInt()
        }
    }

    // 누적합 구하기
    for (i in 1..n) {
        prefixSum[i][1] = territory[i - 1][0]
    }

    for (i in 1..n) {
        for (j in 2..m) {
            prefixSum[i][j] = prefixSum[i][j - 1] + territory[i - 1][j - 1]
        }
    }

    for (i in 2..n) {
        for (j in 1..m) {
            prefixSum[i][j] += prefixSum[i - 1][j]
        }
    }

    // 진경대왕이 인구 수를 궁금해하는 직사각형 범위 구하기
    val k = nextInt()
    val result = Array(size = k) { 0 }
    nextLine() // 공백 제거

    for (i in 0 until k) {

        var (y1, x1, y2, x2) = nextLine().split(' ').map { it.toInt() }

        x1 -= 1
        y1 -= 1

        result[i] = prefixSum[y2][x2] - prefixSum[y2][x1] - prefixSum[y1][x2] + prefixSum[y1][x1]
    }

    for (i in result.indices) {
        println(result[i])
    }

}
