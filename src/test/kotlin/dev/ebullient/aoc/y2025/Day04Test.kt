package dev.ebullient.aoc.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun test() {
        val day04 = Day04()

        val input = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
        """.trimIndent()

        val grid = day04.readString(input)
        grid.draw(' ')
        assertThat(day04.fewerThanFour(grid, false)).isEqualTo(13)
        assertThat(day04.repeat(grid)).isEqualTo(43)
    }

}