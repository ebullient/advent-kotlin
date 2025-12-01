package dev.ebullient.aoc.y2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun day1equals() {
        val day1 = Day01();

        assertThat(day1.rotateEquals(11, Day01.Instruction("R8"))).isEqualTo(19);
        assertThat(day1.rotateEquals(5, Day01.Instruction("L10"))).isEqualTo(95);
        assertThat(day1.rotateEquals(95, Day01.Instruction("R5"))).isEqualTo(0);

        val data = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()
        println(data);

        val instructions = day1.readString(data)

        val equalsZero = day1.part1(instructions)
        assertThat(equalsZero).isEqualTo(3)
    }

    @Test
    fun day1count() {
        val day1 = Day01();

        assertThat(day1.rotateCount(Day01.Amount(0, 0), Day01.Instruction("R150")).passes).isEqualTo(1);
        assertThat(day1.rotateCount(Day01.Amount(0, 0), Day01.Instruction("L50")).passes).isEqualTo(0);
        assertThat(day1.rotateCount(Day01.Amount(0, 0), Day01.Instruction("R50")).passes).isEqualTo(0);

        // ---

        var amount = day1.rotateCount(Day01.Amount(50, 0),
            Day01.Instruction("R150"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(2)

        amount = day1.rotateCount(Day01.Amount(50, 0),
            Day01.Instruction("L150"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(2)

        amount = day1.rotateCount(Day01.Amount(50, 0),
            Day01.Instruction("R50"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(1)

        amount = day1.rotateCount(Day01.Amount(50, 0),
            Day01.Instruction("L50"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(1)

        // ---

        amount = day1.rotateCount(Day01.Amount(50, 0),
            Day01.Instruction("L68"))
        assertThat(amount.dial).isEqualTo(82)
        assertThat(amount.passes).isEqualTo(1)

        amount = day1.rotateCount(Day01.Amount(82, 0),
            Day01.Instruction("L30"))
        assertThat(amount.dial).isEqualTo(52)
        assertThat(amount.passes).isEqualTo(0)

        amount = day1.rotateCount(Day01.Amount(52, 0),
            Day01.Instruction("R48"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(1)

        amount = day1.rotateCount(Day01.Amount(0, 0),
            Day01.Instruction("L5"))
        assertThat(amount.dial).isEqualTo(95)
        assertThat(amount.passes).isEqualTo(0)

        amount = day1.rotateCount(Day01.Amount(95, 0),
            Day01.Instruction("R60"))
        assertThat(amount.dial).isEqualTo(55)
        assertThat(amount.passes).isEqualTo(1)

        amount = day1.rotateCount(Day01.Amount(55, 0),
            Day01.Instruction("L55"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(1)

        amount = day1.rotateCount(Day01.Amount(0, 0),
            Day01.Instruction("L1"))
        assertThat(amount.dial).isEqualTo(99)
        assertThat(amount.passes).isEqualTo(0)

        amount = day1.rotateCount(Day01.Amount(99, 0),
            Day01.Instruction("L99"))
        assertThat(amount.dial).isEqualTo(0)
        assertThat(amount.passes).isEqualTo(1)

        amount = day1.rotateCount(Day01.Amount(0, 0),
            Day01.Instruction("R14"))
        assertThat(amount.dial).isEqualTo(14)
        assertThat(amount.passes).isEqualTo(0)

        amount = day1.rotateCount(Day01.Amount(14, 0),
            Day01.Instruction("L82"))
        assertThat(amount.dial).isEqualTo(32)
        assertThat(amount.passes).isEqualTo(1)

        val data = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()
        println(data);

        val instructions = day1.readString(data)

        val passesZero = day1.part2(instructions)
        assertThat(passesZero).isEqualTo(6)
    }
}