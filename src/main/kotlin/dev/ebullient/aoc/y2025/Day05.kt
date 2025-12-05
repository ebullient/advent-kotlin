package dev.ebullient.aoc.y2025

import dev.ebullient.aoc.Day
import kotlin.math.max
import kotlin.math.min
import kotlin.text.toLong

class Day05: Day<Day05.Inventory>() {
    override fun run() {
        val day05 = Day05()
        val input = day05.readFile("src/main/data/2025/day05.txt")
        println("Day 05, part 1: ${day05.part1(input)}")
        println("Day 05, part 1: ${day05.part2(input)}")
    }

    override fun read(input: List<String>): Inventory {
        return Inventory().build(input)
    }

    fun part1(inventory: Inventory): Long {
        var total = 0L
        for(id in inventory.items) {
            if (inventory.testInRange(id)) {
                total++
            }
        }
        return total
    }

    fun part2(inventory: Inventory): Long {
        var total = 0L
        for(range in inventory.ranges) {
            total += (range.last - range.first + 1)
        }
        return total
    }

    class Inventory() {
        lateinit var ranges: List<LongRange>
        val items = mutableListOf<Long>()

        fun build(input: List<String>): Inventory {
            var separator = false
            var data = mutableListOf<LongRange>()
            for(line in input) {
                if (line.isEmpty()) {
                    separator = true
                } else if (!separator) {
                    // Ranges are before the separator
                    val parts = line.split("-")
                    data.add(LongRange(parts[0].toLong(), parts[1].toLong()))
                } else {
                    // Inventory is after the separator
                    this.items.add(line.toLong())
                }
            }
            this.ranges = collapse(data).toMutableList()
            return this;
        }

        fun testInRange(id: Long): Boolean {
            for (range in this.ranges) {
                if (id in range.first..range.last) {
                    return true
                }
            }
            return false
        }

        fun collapse(data: List<LongRange>): List<LongRange> {
            val sorted = data.sortedBy { it.first }
            var merged = mutableListOf<LongRange>()
            var current = sorted.first()
            for (range in sorted.drop(1)) {
                if (current.last >= range.first) {
                    current = LongRange(current.first, max(current.last, range.last))
                } else {
                    merged.add(current)
                    current = range
                }
            }
            merged.add(current)
            println("$data, $merged")
            return merged
        }
    }
}
