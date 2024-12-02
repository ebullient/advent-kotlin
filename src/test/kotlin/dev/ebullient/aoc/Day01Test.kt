package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun day1ex() {
        val data = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
        println(data);
        val day1 = Day01();

        val locations = day1.readString(data);
        println(locations.list1)
        println(locations.list2)

        val distance = day1.part1(locations);
        assertThat(distance).isEqualTo(11);
        val score = day1.part2(locations);
        assertThat(score).isEqualTo(31);
    }
}