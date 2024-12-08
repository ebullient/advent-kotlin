package dev.ebullient.aoc

import kotlin.math.abs

class Day02 : Day() {
    fun readString(input: String): List<Record> {
        return this.read(toLines(input));
    }

    fun readFile(fileName: String = ""): List<Record> {
        return read(fileToLines(fileName));
    }

    private fun read(input: List<String>): List<Record> {
        val records = mutableListOf<Record>();
        for (line in input) {
            val levels = line.split(spaces).map { it.toInt() }.toIntArray();
            records.add(Record(levels));
        }
        return records;
    }

    fun part1(records: List<Record>): Int {
        return records.filter { it.isSafe() }.count().also {
            println("Safe records: $it");
        }
    }

    fun part2(records: List<Record>): Int {
        return records.filter { it.isMostlySafe() }.count().also {
            println("Mostly safe records: $it");
        }
    }

    @JvmInline
    value class Record(private val levels: IntArray) {
        // find "safe" reports:
        //  - all increasing
        //  - all decreasing
        //  - difference 1 <= x <= 3
        fun isSafe(): Boolean {
            val sequence = levels.asSequence();
            val windows = sequence.windowed(2, 1).map { it[1] - it[0] }
            val increasing = levels[1] > levels[0];
            return windows
                .all { abs(it) in 1..3 && (it > 0) == increasing }
        }

        fun isMostlySafe(): Boolean {
            if (isSafe()) {
                return true;
            }
            for(i in levels.indices) {
                val record = Record(excludeLevel(i));
                if (record.isSafe()) {
                    return true;
                }
            }
            return false;
        }

        private fun excludeLevel(index: Int): IntArray {
            val list = levels.toMutableList();
            list.removeAt(index);
            return list.toIntArray();
        }
    }
}

fun main() {
    val day02 = Day02()
    val records = day02.readFile("src/main/data/day02.txt")
    println("Day 02:")
    day02.part1(records);
    day02.part2(records);
}