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
            //println("comparing $this: $numberOfBalls and $comparison: $comparisonNumber and saying false" )

            return false
        }
    }
    return true
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


fun makeMap(list: List<String>) : MutableMap<String, List<String>> {
    // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    // Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    // -->
    // [Game 1: [3 blue, 4 red,  1 red, 2 green,  6 blue; 2 green],
    // Game 2: [1 blue, 2 green,  3 green, 4 blue, 1 red,  1 green, 1 blue]]
    var gameStats: MutableMap<String, List<String>> = mutableMapOf()
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
            println("This is possible: $game " + game.value.size.toString() + " possible games so far: $possibleGames" )
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

}