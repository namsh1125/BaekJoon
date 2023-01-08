/*
* 백준 17219번. 비밀번호 찾기
* https://www.acmicpc.net/problem/17219
*/

private val password = HashMap<String, String>()

fun main() {

    val siteList = initVariable()
    val result = getResult(siteList)
    printResult(result)
}

fun initVariable(): ArrayList<String> {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }

    repeat(n) {
        val (site, pw) = br.readLine().split(' ')
        password[site] = pw
    }

    val siteList = arrayListOf<String>()
    repeat(m) {
        siteList.add(br.readLine())
    }

    return siteList
}

fun getResult(siteList: ArrayList<String>): ArrayList<String> {

    val result = arrayListOf<String>()

    siteList.forEach {
        result.add(password[it]!!)
    }

    return result
}

fun printResult(result: ArrayList<String>) {

    val bw = System.out.bufferedWriter()
    result.forEach {
        bw.write("$it\n")
    }
    bw.flush()
    bw.close()
}
