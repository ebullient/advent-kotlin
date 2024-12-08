package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.List.copyOf

class Day05Test {
    @Test
    fun day5Test() {
        val day5 = Day05()
        val input = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()

        val allUpdates = day5.readString(input)
        assertThat(day5.middle(allUpdates.updates[0])).isEqualTo(61)
        assertThat(day5.middle(allUpdates.updates[2])).isEqualTo(29)

        assertThat(day5.isCorrect(allUpdates.updates[0], allUpdates.rules)).isTrue()
        assertThat(day5.isCorrect(allUpdates.updates[1], allUpdates.rules)).isTrue()
        assertThat(day5.isCorrect(allUpdates.updates[2], allUpdates.rules)).isTrue()
        assertThat(day5.isCorrect(allUpdates.updates[3], allUpdates.rules)).isFalse()
        assertThat(day5.isCorrect(allUpdates.updates[4], allUpdates.rules)).isFalse()
        assertThat(day5.isCorrect(allUpdates.updates[5], allUpdates.rules)).isFalse()
        assertThat(day5.findCorrectUpdates(allUpdates)).isEqualTo(143)

        val copyupdates = mutableListOf(
               allUpdates.updates[3].copyOf(),
               allUpdates.updates[4].copyOf(),
               allUpdates.updates[5].copyOf()
        )
        val testUpdates = PrinterUpdates(allUpdates.rules, copyupdates)
        assertThat(day5.fixit(testUpdates.updates[0], allUpdates.rules)).isTrue()
        assertThat(day5.fixit(testUpdates.updates[1], allUpdates.rules)).isTrue()
        assertThat(day5.fixit(testUpdates.updates[2], allUpdates.rules)).isTrue()
        assertThat(testUpdates.updates[0])
                .isEqualTo(intArrayOf(97, 75, 47, 61, 53))
        assertThat(testUpdates.updates[1])
                .isEqualTo(intArrayOf(61, 29, 13))
        assertThat(testUpdates.updates[2])
                .isEqualTo(intArrayOf(97, 75, 47, 29, 13))

        assertThat(day5.fixedUpdates(allUpdates)).isEqualTo(123)
    }
}