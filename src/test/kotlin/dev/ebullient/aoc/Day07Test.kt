package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {
    @Test
    fun day7Test() {
        val input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent()

        val day7 = Day07()
        val calibrations = day7.readString(input)

        var result: ULong = day7.calibrate(calibrations, false)
        assertThat(result).isEqualTo(3749uL)

        result = day7.calibrate(calibrations, true)
        assertThat(result).isEqualTo(11387uL)
    }
}