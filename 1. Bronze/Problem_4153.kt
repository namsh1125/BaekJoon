import java.util.Scanner

/*
* 백준 4153번. 직각삼각형
* https://www.acmicpc.net/problem/4153
*/

fun main() = with(Scanner(System.`in`.bufferedReader())){

    val result = arrayListOf<Boolean>()

    do {

        val a = nextInt()
        val b = nextInt()
        val c = nextInt()

        if(a == 0 && b == 0 && c == 0)
            break

        result.add(checkTriangle(a, b, c))

    } while (true)

    result.forEach {
        when(it) {
            true -> println("right")
            false -> println("wrong")
        }
    }
}

fun checkTriangle(a: Int, b: Int, c: Int) : Boolean {

    val size = arrayListOf(a, b, c)
    size.sortDescending()

    return size[0] * size[0] == size[1] * size[1] + size[2] * size[2]
}
