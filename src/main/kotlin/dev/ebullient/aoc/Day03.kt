package dev.ebullient.aoc

import java.nio.file.Files
import java.nio.file.Path

class Day03 : Day() {
    private val part1Regex = """mul\((\d+),(\d+)\)""".toRegex()
    private val obliviate = """don't\(\).*?do\(\)""".toRegex(RegexOption.DOT_MATCHES_ALL)

    fun parse(input: String): List<Multiply> {
        val matchResults = part1Regex.findAll(input)
        return matchResults.map { result -> Multiply(result.groupValues[1].toInt(), result.groupValues[2].toInt()) }
                .toList()
    }

    fun parse2(input: String): List<Multiply> {
        val sparse = obliviate.replace(input, "##")
        return parse(sparse)
    }

    fun combine(input: String, part2: Boolean = false ): Int {
        val instructions = when(part2) {
            true ->  parse2(input)
            false -> parse(input)
        }
        return instructions.map(Multiply::multiply).sum()
    }

    class Multiply(private val a: Int, private val b: Int) {
        fun multiply(): Int {
            return a * b
        }
    }
}

fun main() {
    val day03 = Day03()
    val input = Files.readString(Path.of("src/main/data/day03.txt"))
    println("Day 03:")
    println("Part 1: ${day03.combine(input)}")
    println("Part 2: ${day03.combine(input, true)}")
}