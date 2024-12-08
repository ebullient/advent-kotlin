package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {
    @Test
    fun day6Test() {
        val input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.trimIndent()

        val day6 = Day06()
        val guardField = day6.readString(input)
        println("${guardField.start} is ${guardField.get(guardField.start)}")

        val steps = guardField.patrol()
        println("steps: $steps")
        assertThat(steps).isEqualTo(41)

        val positions = guardField.obstruct()
        assertThat(positions).isEqualTo(6)
    }
}