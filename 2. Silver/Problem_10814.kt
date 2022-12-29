import java.lang.Integer.min
import java.util.Scanner

/*
* 백준 10814번. 나이순 정렬
* https://www.acmicpc.net/problem/10814
*/

data class Member(val age: Int, val name: String)

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val n = nextLine().toInt()
    val memberList = arrayListOf<Member>()

    for (i in 0 until n) {
        val (age, name) = nextLine().split(' ')
        memberList.add(Member(age.toInt(), name))
    }

    memberList.sortBy { it.age }

    memberList.forEach {
        println("${it.age} ${it.name}")
    }
}

