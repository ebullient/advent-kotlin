package dev.ebullient.aoc.y2025

import dev.ebullient.aoc.Day
import dev.ebullient.aoc.y2025.Day01.Instruction
import java.util.Locale.getDefault
import kotlin.math.abs


class Day01 : Day<List<Instruction>>() {
    private val part1Regex = """(L|R)\d+""".toRegex()

    override fun read(input: List<String>): List<Instruction> {
        return input.filter(part1Regex::matches)
            .map { Instruction(it) }
            .toList();
    }

    fun part2(instructions: List<Instruction>): Int {
        var amount = Amount(50, 0);
        var passes = 0
        for (instruction in instructions) {
            amount = rotateCount(amount, instruction)
            passes += amount.passes
        }
        return passes
    }

    fun rotateCount(amount: Amount, instruction: Instruction): Amount {
        var next = amount.dial
        if (instruction.direction == Direction.R) {
            next += instruction.distance
        } else {
            next -= instruction.distance
        }

        var passes = if (
            (next < 0 && amount.dial != 0)
            || next == 0
        ) 1 else 0
        passes += abs(next / 100)

        println("instruction: $instruction, ${amount.dial} -> $next, $passes")
        return Amount(((next % 100) + 100) % 100, passes);
    }

    fun part1(instructions: List<Instruction>): Int {
        var dial = 50
        var equals = 0
        for (instruction in instructions) {
            dial = rotateEquals(dial, instruction)
            if (dial == 0) {
                equals++
            }
        }
        return equals
    }

    fun rotateEquals(dial: Int, instruction: Instruction): Int {
        var next: Int
        if (instruction.direction == Direction.R) {
            next = dial + instruction.distance
        } else {
            next = dial - instruction.distance
        }

        return ((next % 100) + 100) % 100
    }

    class Amount(val dial: Int, val passes: Int) {
        override fun toString(): String {
            return "dial=$dial, amount=$passes"
        }
    }

    enum class Direction {
        R, L
    }

    class Instruction(
        val direction: Direction,
        val distance: Int
    ) {
        constructor(instruction: String) : this(
            direction = Direction.valueOf(instruction.take(1).uppercase(getDefault())),
            distance = instruction.drop(1).toInt()
        )

        override fun toString(): String {
            return "$direction $distance"
        }
    }
}

fun main() {
    val day01 = Day01();
    val instructions = day01.readFile("src/main/data/2025/day01.txt");
    var password = day01.part1(instructions);
    println("Day 01, part 1: $password")
    password = day01.part2(instructions);
    println("Day 01, part 2: $password")
}