import java.util.*

/*
* 백준 1427번. 소트인사이드
* https://www.acmicpc.net/problem/1427
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val num = nextLine().toCharArray()

    num.sort()
    num.reverse()

    for(i in num.indices) {
        print(num[i])
    }

}
