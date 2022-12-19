/*
* 백준 10988번. 팰린드롬인지 확인하기
* https://www.acmicpc.net/problem/10988
*/
fun main(args: Array<String>) {

    val vocab = readLine()
    var result: Boolean = true

    for (i in 0 until vocab!!.length) {
        if (vocab[i] != vocab[vocab.length - i - 1]) {
            result = false
        }
    }

    when (result) {
        false -> println(0)
        else -> println(1)
    }

}
