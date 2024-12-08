package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun day2ex1() {
        // report per line; levels
        // find "safe" reports:
        //  - all increasing
        //  - all decreasing
        //  - difference 1 <= x <= 3
        val data = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent();
        val day2 = Day02();
        val records = day2.readString(data);

        val safe = day2.part1(records);
        assertThat(safe).isEqualTo(2);

        val mostlySafe = day2.part2(records);
        assertThat(mostlySafe).isEqualTo(4);
    }
}