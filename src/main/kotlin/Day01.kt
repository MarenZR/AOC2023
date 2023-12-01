import java.io.File

// Day 1: Trebuchet
//Part one

val data = readFileAsLinesUsingUseLines("src/day1.txt")
val data2 = readFileAsLinesUsingUseLines("src/day1part2.txt")
fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
fun findCalibrationValue(numbers: List<String>): Int {
    return numbers.map {(it.first().toString() + it.last()) }.sumOf { it.toInt() }
}

// Part two

val numbers = mapOf(
    "one" to "one1one",
    "two" to "two2two",
    "three" to "three3three",
    "four" to "four4four",
    "five" to "five5five",
    "six" to "six6six",
    "seven" to "seven7seven",
    "eight" to "eight8eight",
    "nine" to "nine9nine",
)
fun findFirstAndLastNumbers(list: List<String>): List<String> {
    return list
        .map { numbers.entries.fold(it) { acc, (key, value) ->
            acc.replace(key, value) }
        }
        .map { it.filter { char -> char.isDigit() } }
}

fun main() {
    val numbersPartOne = data.map { it.filter { char -> char.isDigit() } }
    val partOne = findCalibrationValue(numbersPartOne)
    println(partOne)
    val numbersPartTwo = findFirstAndLastNumbers(data)
    val partTwo = findCalibrationValue(numbersPartTwo)
    println(partTwo)
}