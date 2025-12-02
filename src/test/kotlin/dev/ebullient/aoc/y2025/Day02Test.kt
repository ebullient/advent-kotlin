package dev.ebullient.aoc.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun test() {
        val day02 = Day02();

        // Single line (actually)
        // The ranges are separated by commas (,); each range gives its first ID and last ID separated by a dash (-).
        val exampleInput = """
            11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
            1698522-1698528,446443-446449,38593856-38593862,565653-565659,
            824824821-824824827,2121212118-2121212124
            """.trimIndent();

        val parsedInput = day02.readString(exampleInput)
        assertThat(day02.part1(parsedInput)).isEqualTo(1227775554)
        assertThat(day02.part2(parsedInput)).isEqualTo(4174379265)
    }
}