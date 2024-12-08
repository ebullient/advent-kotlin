package dev.ebullient.aoc

import dev.ebullient.aoc.Day03.Multiply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun day3part1() {
        val day3 = Day03();
        assertThat(day3.combine("mul(44,46)")).isEqualTo(2024);
        assertThat(day3.combine("mul(123,4)")).isEqualTo(492);

        assertThat(day3.parse("mul(4*")).isEmpty();
        assertThat(day3.parse("mul(6,9!")).isEmpty();
        assertThat(day3.parse("?(12,34)")).isEmpty();
        assertThat(day3.parse("mul ( 2 , 4 )")).isEmpty();

        val ex = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        val instructions = day3.parse(ex);
        assertThat(instructions.size).isEqualTo(4);
        val result = instructions.map(Multiply::multiply).sum();
        assertThat(result).isEqualTo(161);
        assertThat(day3.combine(ex)).isEqualTo(161);
    }

    @Test
    fun day3part2() {
        val day3 = Day03();
        val ex = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        val instructions = day3.parse2(ex);
        assertThat(instructions.size).isEqualTo(2);
        val result = instructions.map(Multiply::multiply).sum();
        assertThat(result).isEqualTo(48);
        assertThat(day3.combine(ex, true)).isEqualTo(48);
    }
}