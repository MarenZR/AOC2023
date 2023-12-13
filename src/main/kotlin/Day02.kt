//Day 2: Cube Conundrum

val day2test1 = readFileAsLinesUsingUseLines("src/day2test1.txt")
val day2part1 = readFileAsLinesUsingUseLines("src/day2part1.txt")

fun String.color() = this.split(" ").last()

fun String.number() = this.trim().split(" ").first()

fun String.isPossible(comparison: String): Boolean {
    val colorsMatch = this.color() == comparison.color()
    if (colorsMatch){
        val numberOfBalls = this.number().toInt()
        val comparisonNumber = comparison.number().toInt()
        if (numberOfBalls > comparisonNumber) {
            return false
        }
    }
    return true
}

fun List<String>.powerOfBalls(): Int {
    var red = 0
    var blue = 0
    var green = 0
    this.forEach { s ->
        val balls = s.split(",")
        balls.forEach {
            if (it.color() == "red") {
                if (it.number().toInt() > red) red = it.number().toInt()
            }
            if (it.color() == "blue") {
                if (it.number().toInt() > blue) blue = it.number().toInt()
            }
            if (it.color() == "green") {
                if (it.number().toInt() > green) green = it.number().toInt()
            }
        }
    }
    return red * blue * green
}

fun List<String>.hasPossibleGames(comparison: String): Boolean {
    this.forEach { draw ->
        val balls = draw.split(", ")
        if ( balls.filter { it.isPossible(comparison) }.size < balls.size) {
            return false
        }
    }
    return true
}

fun getSumOfPowers(map: Map<String, List<String>>): Long {
    var sum: Long = 0
    map.forEach { (_, u) ->
        sum += u.powerOfBalls().toLong()
    }
    return sum
}


fun makeMap(list: List<String>) : MutableMap<String, List<String>> {
    // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    // Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    // -->
    // [Game 1: [3 blue, 4 red,  1 red, 2 green,  6 blue; 2 green],
    // Game 2: [1 blue, 2 green,  3 green, 4 blue, 1 red,  1 green, 1 blue]]
    val gameStats: MutableMap<String, List<String>> = mutableMapOf()
    list.forEach {
        val game = it.split(":").first
        val stuff = it.split(":").last.split(";")
        gameStats[game] = stuff
    }
    return gameStats
}


fun getPossibleGames(stats: MutableMap<String, List<String>>, red: String, green: String, blue: String): Int {
    var possibleGames = 0
    stats.forEach { game ->
        if (game.value.hasPossibleGames(red) && game.value.hasPossibleGames(green) && game.value.hasPossibleGames(blue)) {
            possibleGames += game.key.split(" ").last.toInt()
        }
    }
    return possibleGames
}

fun main() {
    val testStats = makeMap(day2test1)
    println(getPossibleGames(testStats, "12 red", "13 green", "14 blue")) //should be 8
    val stats = makeMap(day2part1)
    println(getPossibleGames(stats, "12 red", "13 green", "14 blue")) //should be 2164
    println(getSumOfPowers(testStats))
    println(getSumOfPowers(stats))

}