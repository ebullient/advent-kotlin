package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day04Test {
    @Test
    fun day4Test() {
        val day4 = Day04()
        val input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent()

        val grid = day4.readString(input)
        grid.print()
        assertThat(grid.walk()).isEqualTo(18)
        assertThat(grid.walk2()).isEqualTo(9)
    }
}