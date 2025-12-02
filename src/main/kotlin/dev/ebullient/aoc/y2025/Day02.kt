package dev.ebullient.aoc.y2025

import dev.ebullient.aoc.Day

class Day02 : Day<List<Day02.Range>>() {

    override fun run() {
        val day02 = Day02();
        val input = day02.readFile("src/main/data/2025/day02.txt")
        println("Day 02, part 1: ${day02.part1(input)}")
        println("Day 02, part 2: ${day02.part2(input)}")
    }

    override fun read(input: List<String>): List<Day02.Range> {
        return input
            .flatMap { it.split(",") }
            .filter(String::isNotEmpty)
            .map { Range(it) }
            .toList()
    }

    fun part1(input: List<Range>): Long {
        val matches = ArrayList<Long>()
        range@ for (range in input) {
            number@ for (number in range.start..range.end) {
                val actual = number.toString()
                if (actual.length % 2 == 0) {
                    val half = actual.length / 2
                    val expected = actual.take(half).repeat(2)
                    if (expected == actual) {
                        matches.add(number)
                    }
                }
            }
        }
        return matches.sum()
    }

    fun part2(input: List<Range>): Long {
        val matches = ArrayList<Long>()
        range@ for (range in input) {
            number@ for (number in range.start..range.end) {
                val actual = number.toString()
                pattern@ for(i in 1 ..actual.length/2) {
                    if (actual.length % i == 0) {
                        val n = actual.length / i
                        val pattern = actual.take(i).repeat(n)
                        if (actual == pattern) {
                            matches.add(number)
                            break@pattern
                        }
                    }
                }
            }
        }
        return matches.sum()
    }

    class Range(val start: Long, val end: Long) {
        constructor(text: String) : this(
            text.substringBefore('-').toLong(),
            text.substringAfter('-').toLong()
        )

        fun size(): Long {
            return end - start
        }

        override fun toString(): String {
            return "Range(" +
                    "start=$start, " +
                    "end=$end, " +
                    "size=${size()}" +
                    ")"
        }
    }
}