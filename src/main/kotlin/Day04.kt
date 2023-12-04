//Day 04: Scratchcards

val day4test1 = readFileAsLinesUsingUseLines("src/day4test1.txt")
val day4part1 = readFileAsLinesUsingUseLines("src/day4part1.txt")

fun parsedInput(input: List<String>): List<String> {
    val list = input.map {
        it
            .split(":").last().trim().split("|").first
            .trim().split(" ")
            .intersect(it.split(":").last().trim().split("|").last.trim().split(" ").toSet()).toString()
    }

    return list.filter { it.contains("-?[0-9]+(\\.[0-9]+)?".toRegex()) }

}

fun countWinningNumbers(parsedInput: List<String>): Int {
    var score = 0
    parsedInput.forEach { it ->
        val winningNumbers = it.trim().replace(", ,", "").replace(",", "").split(" ")
        println(winningNumbers)
        var gameScore = 0
        if (winningNumbers.isNotEmpty()) {
            winningNumbers.forEach {
                println(it + " so far sum is $score" + "gamescore: $gameScore")
                val number = it.filter {  char -> char.isDigit() }
                if (number.isNotEmpty()){
                    if (gameScore == 0) {
                        gameScore += 1
                    } else {
                        gameScore = gameScore * 2
                    }
                }
            }
            score += gameScore
        }

    }
    return score
}


fun main() {
    println(countWinningNumbers(parsedInput(day4test1)))
    println(countWinningNumbers(parsedInput(day4part1)))

}