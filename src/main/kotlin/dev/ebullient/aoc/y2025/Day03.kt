package dev.ebullient.aoc.y2025

import dev.ebullient.aoc.Day
import kotlin.math.max

class Day03: Day<List<String>>() {
    override fun run() {
        val day03 = Day03()
        val input = day03.readFile("src/main/data/2025/day03.txt")
        println("Day 03, part 1: ${day03.part1(input)}")
        println("Day 03, part 2: ${day03.part2(input)}")
    }

    override fun read(input: List<String>): List<String> {
        return input.filter(String::isNotBlank)
    }

    fun batteryRatings(batteries: String): List<String> {
        return batteries
            .split("")
            .map {it.trim()}
            .filter { !it.isEmpty() }
    }

    fun part1(banks: List<String>): Int {
        return banks.map { bankPower(it) }.sum()
    }

    fun bankPower(batteries: String): Int {
        val ratings = batteryRatings(batteries)

        var joltage = 0
        for(i in 0..<ratings.size) {
            for (j in i+1 ..<ratings.size) {
                joltage = max(joltage, (ratings[i] + ratings[j]).toInt())
            }
        }
        return joltage
    }

    // turn on 12 batteries in the bank
    fun extraBankPower(batteries: String): Long {
        cache.clear()
        return evaluateBank(batteries.trim(), 0, 12, "").toLong()
    }

    var cache = mutableMapOf<Pair<Int, Int>, String>()

    fun evaluateBank(ratings: String, i: Int, remaining: Int, prefix: String): String {
        if (remaining == 0) {
            return prefix
        }
        val key = Pair(i, remaining)
        if (cache.containsKey(key)) {
            return prefix + cache[key]!!
        }

        if (i + remaining < ratings.length) {
            val leave = evaluateBank(ratings, i + 1, remaining, prefix)
            val take = evaluateBank(ratings, i + 1, remaining - 1, prefix + ratings[i])
            val result = if (leave.toLong() > take.toLong() ) leave else take
            cache[key] = result.substring(prefix.length)
            return result
        }
        cache[Pair(i, remaining)] = ratings.substring(i)
        return (prefix + ratings.substring(i))
    }

    fun part2(banks: List<String>): Long {
        return banks.map { extraBankPower(it) }.sum()
    }
}