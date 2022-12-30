import java.lang.Integer.max

/*
* 백준 1516번. 게임 개발
* https://www.acmicpc.net/problem/1516
*/

data class Building(val num: Int, val buildTime: Int, val parents: ArrayDeque<Int>, var time: Int) // 건설 시간, 선행 건물, 짓는데 필요한 시간

private val buildingList = arrayListOf<Building>()

fun main() {

    initVariable()
    solveProblem()
    printResult()
}

fun initVariable() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    for (i in 0 until n) {

        val line = br.readLine()!!.split(' ').map { it.toInt() }
        val buildTime = line[0]
        val parent = ArrayDeque<Int>()

        var j = 1
        while (line[j] != -1) {
            parent.add(line[j])
            j++
        }

        buildingList.add(Building(i + 1, buildTime, parent, 0))
    }
}

fun solveProblem() {

    val queue = buildingList.filter { it.parents.size == 0 } as ArrayList // 선행 건물이 없는 건물 추가

    // 위상 정렬
    while (queue.isNotEmpty()) {

        val building = queue.removeFirst()
        building.time += building.buildTime

        for(i in buildingList.indices) {

            // 방금 지어진 건물이 선행 건물이라면 제거
            if(buildingList[i].parents.contains(building.num)) {

                buildingList[i].time = max(building.time, buildingList[i].time)
                buildingList[i].parents.remove(building.num)

                // 선행 건물이 없는 경우 queue에 추가
                if(buildingList[i].parents.size == 0) {
                    queue.add(buildingList[i])
                }
            }
        }
    }

}

fun printResult() {

    val bw = System.out.bufferedWriter()
    buildingList.forEach {
        bw.write("${it.time}\n")
    }
    bw.flush()
    bw.close()
}
