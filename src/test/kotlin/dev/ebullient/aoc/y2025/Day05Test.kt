package dev.ebullient.aoc.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun test() {
        val day05 = Day05()
        val input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """.trimIndent()

        val inventory = day05.readString(input)
        assertThat(inventory.testInRange(1)).isFalse()
        assertThat(inventory.testInRange(5)).isTrue()
        assertThat((day05.part1(inventory))).isEqualTo(3)
        assertThat((day05.part2(inventory))).isEqualTo(14)
    }
}