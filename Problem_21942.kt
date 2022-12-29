import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.HashMap

/*
* 백준 21942번. 부품 대여장
* https://www.acmicpc.net/problem/21942
*/

data class Key(val item: String, val member: String)

private val rentalList = HashMap<Key, LocalDateTime>()
private var time = 0
private var fine = 0L
private val fineList = LinkedHashMap<String, Long>()

fun main() = with(Scanner(System.`in`.bufferedReader())) {

    val (n, l, f) = nextLine().split(' ')

    val rentalDay = l.split('/') // year, month, day
    val rentalMin = rentalDay[1].split(':').map { it.toInt() } // hour, min
    time = rentalDay[0].toInt() * 1440 + rentalMin[0] * 60 + rentalMin[1]

    fine = f.toLong()

    for (i in 0 until n.toInt()) {

        val line = nextLine().split(' ')
        val day = line[0].split('-').map { it.toInt() }
        val hour = line[1].split(':').map { it.toInt() }
        val key = Key(item = line[2], member = line[3])
        val date = LocalDateTime.of(day[0], day[1], day[2], hour[0], hour[1])

        if(rentalList.containsKey(key)) {

            val rentalTime = getRentalTime(rentalList.remove(key)!!, date)

            if (time < rentalTime) {

                val member = line[3]
                if(fineList.containsKey(member)) {
                    fineList[member] = fineList[member]!! + (rentalTime - time) * fine
                } else {
                    fineList[member] = (rentalTime - time) * fine
                }
            }
        } else {
            rentalList[key] = date
        }
    }

    printResult()
}

fun getRentalTime(start: LocalDateTime, end: LocalDateTime): Long {
    return ChronoUnit.MINUTES.between(start, end)
}

fun printResult() {

    val sortedMap = TreeMap(fineList)

    val bw = System.out.bufferedWriter()

    if (sortedMap.isEmpty()) {
        bw.write("-1\n")
    } else {
        sortedMap.forEach {
            bw.write("${it.key} ${it.value}\n")
        }
    }
    bw.flush()
    bw.close()
}
