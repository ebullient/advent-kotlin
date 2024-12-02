package dev.ebullient.aoc

import kotlin.math.abs

class Day01 : Day()  {

    fun readString(input: String): Locations {
        return this.read(toLines(input));
    }

    fun readFile(fileName: String = ""): Locations {
        return read(fileToLines(fileName));
    }

    private fun read(input: List<String>): Locations {
        val locations = Locations();
        for (line in input) {
            val pair = line.split(spaces);
            if (pair.size < 2) {
                continue;
            }
            locations.list1.add(pair[0].toInt());
            locations.list2.add(pair[1].toInt());
        }
        locations.list1.sort();
        locations.list2.sort();
        return locations;
    }

    fun part1(locations: Locations): Int {
        var distance: Int = 0
        locations.list1.zip(locations.list2) { a, b ->
            distance += abs(a - b)
        };
        println("Differences between pairs: $distance");
        return distance;
    }

    fun part2(locations: Locations): Int {
        // exactly how often each number from the left list appears in the right list
        // similarity score: sum of (list1 * #of times in list2)
        val map = mutableMapOf<Int, Int>();
        locations.list2.forEach { n -> map.put(n, map.getOrDefault(n, 0) + 1) };
        var score = 0;
        locations.list1.forEach { n -> score += n * map.getOrDefault(n, 0) };
        println("Similarity score: $score");
        return score;
    }

    class Locations {
        val list1 = mutableListOf<Int>();
        val list2 = mutableListOf<Int>();
    }
}

fun main() {
    val day01 = Day01();
    val locations = day01.readFile("src/main/data/day01.txt");
    println("Day 01:")
    day01.part1(locations);
    day01.part2(locations);
}