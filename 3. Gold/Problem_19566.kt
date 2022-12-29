import java.util.Scanner

/*
* 백준 19566번. 수열의 구간 평균
* https://www.acmicpc.net/problem/19566
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (_, average) = nextLine().split(' ').map { it.toInt() }
    val arr = nextLine().split(' ').map { it.toInt() }.toMutableList()

    // 입력 받은 모든 값에 대해 구하고자 하는 평균 빼기
    //  -> 구간에 있는 모든 수들의 평균이 0인 구간의 개수 찾기
    for (i in arr.indices) {
        arr[i] -= average
    }

    // 누적합 구하기
    val prefixSum = Array(size = arr.size) { 0L }
    prefixSum[0] = arr[0].toLong()

    for (i in 1 until arr.size) {
        prefixSum[i] = prefixSum[i - 1] + arr[i]
    }

    // 결과 구하기
    // 구간에 있는 모든 수의 평균이 0이 되는 경우는 아래 2가지 경우이다
    //  - 누적합이 0인 경우
    //  - 누적합이 같은 값을 서로 뺐을 때
    // 따라서 누적합이 0인 경우와 같은 값의 누적합을 2개 이상 가진 경우를 찾아야 한다

    // 누적합에 어떤 값이 몇개 있는지 찾기
    val distinct = prefixSum.distinct()
    val map = mutableMapOf<Long, Long>()

    for (i in distinct.indices) {
        map[distinct[i]] = 0
    }

    for (i in prefixSum.indices) {

        val count = map.get(key = prefixSum[i])!! + 1
        map.remove(key = prefixSum[i])
        map[prefixSum[i]] = count
    }

    // 결과 출력
    var result = 0L
    for (i in distinct.indices) {

        if (distinct[i] == 0L) { // 누적합이 0인 경우

            val count = map.get(key = distinct[i])!!
            result += count + count * (count - 1) / 2 // nC0 + nC1

        } else if (map[distinct[i]]!! >= 2) { // 누적합이 0이 아니고 2개 이상인 경우

            val count = map.get(key = distinct[i])!!
            result += count * (count - 1) / 2 // nC1

        }
    }

    println(result)

}
