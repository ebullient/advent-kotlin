package dev.ebullient.aoc.y2025

import dev.ebullient.aoc.Day
import dev.ebullient.aoc.Grid
import dev.ebullient.aoc.Point

class Day04: Day<Grid>() {
    override fun run() {
        val day04 = Day04()
        val input = day04.readFile("src/main/data/2025/day04.txt")
        println("Day 04, part 1: ${day04.fewerThanFour(input, false)}")
        println("Day 04, part 2: ${day04.repeat(input)}")
    }

    override fun read(input: List<String>): Grid {
        return Grid().build(input)
    }

    fun repeat(grid: Grid): Int {
        var total = 0
        var eligible = 0

        do {
            eligible = fewerThanFour(grid, true)
            total += eligible
        } while (eligible != 0)

        return total
    }

    fun fewerThanFour(grid: Grid, write: Boolean): Int {
        val eligibleRolls = mutableListOf<Point>()
        grid.walk { point, char ->
            run {
                if (char == '@') {
                    val paperHere = grid.box(point).stream()
                            .filter { p -> grid.get(p) == '@' }
                            .count()
                    if (paperHere < 4) {
                        eligibleRolls.add(point)
                    }
                } else if (write && char == 'x') {
                    grid[point] = '.'
                }
            }
        }
        if (write) {
            eligibleRolls.forEach { eligible -> grid[eligible] = 'x' }
        }
        println("-----")
        grid.draw(' ')
        return eligibleRolls.size
    }
}