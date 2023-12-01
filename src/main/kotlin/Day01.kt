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
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
)
fun findFirstAndLastNumbers(list: List<String>): List<String> {
    val reversed = list.map {
        numbers.entries.reversed()
            .fold(it) { acc, (key, value) -> acc.replace(key, value)}
    }
    val forwardList = list.map {
        numbers.entries
            .fold(it) { acc, (key, value) -> acc.replace(key, value)}
    }

    val lastNumbers: MutableList<String> = ArrayList()
    reversed.forEachIndexed { index, s ->
        lastNumbers.add(index, getLastNumberString(forwardList[index], s))
    }

    val firstNumbers: MutableList<String> = ArrayList()
    reversed.forEachIndexed { index, s ->
        firstNumbers.add(index, getFirstNumberString(forwardList[index], s))
    }

    val firsts = extractFirst(firstNumbers)
    val lasts = extractLast(lastNumbers)

    return firsts.zip(lasts) { a, b -> "$a$b"}
}

fun extractLast(numbers: List<String>): List<String> {
    return numbers.map { it.last { char -> char.isDigit() }.toString() }
}

fun extractFirst(numbers: List<String>): List<String> {
    return numbers.map { it.first { char -> char.isDigit() }.toString() }
}

fun getLastNumberString(first: String, second: String): String {
    val indexFirst = first.indexOfLast { it.isDigit() }
    val indexLast = second.indexOfLast { it.isDigit() }
    if (indexFirst > indexLast) {
        return first
    } else {
        return second
    }
}
fun getFirstNumberString(first: String, second: String): String {
    val indexFirst = first.indexOfFirst { it.isDigit() }
    val indexLast = second.indexOfFirst { it.isDigit() }
    if (indexFirst < indexLast) {
        return first
    } else {
        return second
    }
}

fun main() {
    val numbersPartOne = data.map { it.filter { char -> char.isDigit() } }
    val partOne = findCalibrationValue(numbersPartOne)
    println(partOne)
    val numbersPartTwo = findFirstAndLastNumbers(data)
    val partTwo = findCalibrationValue(numbersPartTwo)
    println(partTwo)
}