package dev.ebullient.aoc

typealias Operation = (ULong, ULong) -> ULong

val ADD: Operation = { a, b -> a + b }
val MULT: Operation = { a, b -> a * b }
val CAT: Operation = { a, b -> "${a}${b}".toULong() }

data class CalibrationResult(val result: ULong, val readings: List<ULong>)

class Day07 : Day() {
    fun readString(input: String): List<CalibrationResult> {
        return read(toLines(input))
    }

    fun readFile(fileName: String = ""): List<CalibrationResult> {
        return read(fileToLines(fileName))
    }

    private fun read(input: List<String>): List<CalibrationResult> {
        return input.map { line ->
            val parts = line.split(" ")
            val result = parts[0].replace(":", "").toULong()
            val readings = parts.subList(1, parts.size).map { it.toULong() }
            CalibrationResult(result, readings)
        }
    }

    private fun accumulate(acc: ULong, index: Int, calibration: CalibrationResult, cat: Boolean): ULong {
        if (acc == calibration.result && index >= calibration.readings.size) {
            return 1u // yay!
        } else if (acc > calibration.result || index >= calibration.readings.size) {
            return 0u // nope
        }
        val value = calibration.readings[index]
        val next = index + 1
        return accumulate(ADD(acc, value), next, calibration, cat) +
                accumulate(MULT(acc, value), next, calibration, cat) +
                when(cat) {
                    true -> accumulate(CAT(acc, value), next, calibration, true)
                    false -> 0u
                }
    }


    fun calibrate(input: List<CalibrationResult>, cat: Boolean): ULong {
        var sum: ULong = 0u
        for (calibration in input) {
            if (accumulate(calibration.readings[0], 1, calibration, cat) > 0u) {
                sum += calibration.result
            }
        }
        return sum
    }
}

fun main() {
    val day07 = Day07()
    val calibrations = day07.readFile("src/main/data/day07.txt")

    println("Day 07:")
    println("Part 1: ${day07.calibrate(calibrations, false)}")
    println("Part 2: ${day07.calibrate(calibrations, true)}")
}