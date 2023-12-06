// Day 06: Wait For It

val day6test1 = readFileAsLinesUsingUseLines("src/day6test1.txt")
val day6part1 = readFileAsLinesUsingUseLines("src/day6part1.txt")

fun main() {
    var data = evaluateData(day6test1)
    println(marginOfError(data))
    data = evaluateData(day6part1)
    println(marginOfError(data))
    data = evaluateDataPartTwo(day6part1)
    val dataKey = data.keys.first()
    println(calculateWins(dataKey.toLong(), data[dataKey]!!.toLong()))
}

fun evaluateData(input: List<String>): Map<String, String> {
    val times = input.first.split(":").last.trim().split("\\s+".toRegex())
    val distances = input.last.split(":").last.trim().split("\\s+".toRegex())
    val map = times.zip(distances).toMap()
    return map
}
fun evaluateDataPartTwo(input: List<String>): Map<String, String> {
    val time = input.first.split(":").last.trim().replace("\\s+".toRegex(), "")
    val distance = input.last.split(":").last.trim().replace("\\s+".toRegex(), "")
    val map = mapOf( time to distance )
    return map
}

fun marginOfError(timesAndDistances: Map<String, String>): Long {
    var margin: MutableList<Int> = mutableListOf()
    timesAndDistances.forEach { (time, dist) ->
        margin.add(calculateWins(time.toLong(), dist.toLong()))
    }
    return margin.reduce { acc, i ->  acc * i }.toLong()
}

fun calculateWins(time: Long, distance: Long): Int {
    var speed = 0
    var distances: MutableList<Long> = mutableListOf()
    for(i in 0 .. time) {
        distances.add(speed * (time - i))
        speed += 1
    }
    return distances.filter { (it > distance) }.size
}

