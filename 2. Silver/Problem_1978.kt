import java.util.*
import kotlin.math.sqrt

/*
* 백준 1978번. 소수 찾기
* https://www.acmicpc.net/problem/1978
*/

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLine().toInt()
    val num = nextLine().split(' ').map { it.toInt() }
    var result = 0

    num.forEach {
        if(checkPrimeNumber(it)) {
            result ++
        }
    }

    val bw = System.out.bufferedWriter()
    bw.write("$result\n")
    bw.flush()
    bw.close()
}

fun checkPrimeNumber(num: Int): Boolean {

    if(num == 1) return false
    else {

        for(i in 2 .. sqrt(num.toDouble()).toInt()) {
            if(num % i == 0) return false
        }

        return true
    }
}
