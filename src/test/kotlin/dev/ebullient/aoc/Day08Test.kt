package dev.ebullient.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day08Test {
    @Test
    fun day8Test() {
        // two antennas with frequency 'a'
        // create two antiNodes marked with #
        val ex = """
            ..........
            ...#......
            ..........
            ....a.....
            ..........
            .....a....
            ..........
            ......#...
            ..........
            ..........
        """.trimIndent()

        val day8 = Day08()
        val map = day8.readString(ex)

        val pairs = (map.antennas['a'] ?: emptyList()).pairs()
        assertThat(pairs).hasSize(1)

        val antiNodes = day8.findAntiNodes(map)

        val resultMap = antiNodes.second.toString('.', map)
        assertThat(resultMap.trim()).isEqualTo(ex)

        assertThat(antiNodes.first).isEqualTo(2)
    }

    @Test
    fun day8Test2() {
        // third antenna with the same frequency creates several more antinodes.
        // It would ideally add four antinodes, map bounds -- adds only two

        val ex = """
            ..........
            ...#......
            #.........
            ....a.....
            ........a.
            .....a....
            ..#.......
            ......#...
            ..........
            ..........
        """.trimIndent()

        val day8 = Day08()
        val map = day8.readString(ex)

        val pairs = (map.antennas['a'] ?: emptyList()).pairs()
        assertThat(pairs).hasSize(3)

        val antiNodes = day8.findAntiNodes(map)

        val resultMap = antiNodes.second.toString('.', map)
        assertThat(resultMap.trim()).isEqualTo(ex)

        assertThat(antiNodes.first).isEqualTo(4)
    }

    @Test
    fun day8Test3() {
        // Antennas with different frequencies don't create antiNodes;
        // A and a count as different frequencies.
        // However, antiNodes can occur at locations that contain
        // antennas
        // the lone antenna with frequency capital A creates no antiNodes
        // but has a lowercase-a-frequency antiNodes at its location

        val ex = """
            ..........
            ...#......
            #.........
            ....a.....
            ........a.
            .....a....
            ..#.......
            ......A...
            ..........
            ..........
        """.trimIndent()

        val day8 = Day08()
        val map = day8.readString(ex)

        val antiNodes = day8.findAntiNodes(map)

        val resultMap = antiNodes.second.toString('.', map)
        assertThat(resultMap.trim()).isEqualTo(ex)

        assertThat(antiNodes.first).isEqualTo(4)
    }

    @Test
    fun day8Test4() {
        val ex = """
            ......#....#
            ...#....0...
            ....#0....#.
            ..#....0....
            ....0....#..
            .#....A.....
            ...#........
            #......#....
            ........A...
            .........A..
            ..........#.
            ..........#.
        """.trimIndent()

        val day8 = Day08()
        val map = day8.readString(ex)
        // 14 total unique locations
        val antiNodes = day8.findAntiNodes(map)

        val resultMap = antiNodes.second.toString('.', map)
        assertThat(resultMap.trim()).isEqualTo(ex)

        assertThat(antiNodes.first).isEqualTo(14)
    }

    @Test
    fun day8TestPart2() {
        val ex = """
            ##....#....#
            .#.#....0...
            ..#.#0....#.
            ..##...0....
            ....0....#..
            .#...#A....#
            ...#..#.....
            #....#.#....
            ..#.....A...
            ....#....A..
            .#........#.
            ...#......##
        """.trimIndent()

        val day8 = Day08()
        val map = day8.readString(ex)

        val antiNodes = day8.findAllAntiNodes(map)

        val resultMap = antiNodes.second.toString('.', map)
        assertThat(resultMap.trim()).isEqualTo(ex)

        assertThat(antiNodes.first).isEqualTo(34)
    }
}