package dev.ebullient.aoc.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day03Test {

    @Test
    fun part1() {
        val day = Day03()

        // each labeled with their rating, a value from 1 to 9.
        // arranged in banks: each line is a bank
        // part 1: turn on exactly two, find largest possible

        val ex1 = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent().split("\n")

        assertThat(day.bankPower(ex1[0])).isEqualTo(98)
        assertThat(day.bankPower(ex1[1])).isEqualTo(89)
        assertThat(day.bankPower(ex1[2])).isEqualTo(78)
        assertThat(day.bankPower(ex1[3])).isEqualTo(92)
        assertThat(day.part1(ex1)).isEqualTo(357)

        assertThat(day.extraBankPower(ex1[0])).isEqualTo(987654321111)
        assertThat(day.extraBankPower(ex1[1])).isEqualTo(811111111119)
        assertThat(day.extraBankPower(ex1[2])).isEqualTo(434234234278)
        assertThat(day.extraBankPower(ex1[3])).isEqualTo(888911112111)
        assertThat(day.part2(ex1)).isEqualTo(3121910778619)
    }
}