package dev.ebullient.aoc

class Day04 : Day() {
    fun readString(input: String): XMasGrid {
        return XMasGrid(toLines(input))
    }

    fun readFile(fileName: String = ""): XMasGrid {
        return XMasGrid(fileToLines(fileName))
    }
}

class XMasGrid(input: List<String>) : Grid() {
    private val xmas = "XMAS"

    init {
        build(input)
    }

    private fun test(p: Point, step: (Point) -> Point, inRange: Boolean): Int {
        if (!inRange) {
            return 0
        }
        var i = 0
        var pos = p
        while (get(pos) == xmas[i]) {
            pos = step(pos)
            i++
            if (i == xmas.length) {
                return 1
            }
        }
        return 0
    }

    private fun testMAS(p: Point): Int {
        val x1Top = get(p.nw())
        val x1Bot = get(p.se())
        val x2Top = get(p.ne())
        val x2Bot = get(p.sw())
        val x1ok = (x1Top == 'M' && x1Bot == 'S') || (x1Top == 'S' && x1Bot == 'M')
        val x2ok = (x2Top == 'M' && x2Bot == 'S') || (x2Top == 'S' && x2Bot == 'M')
        return if (x1ok && x2ok) 1 else 0
    }

    fun walk(): Int {
        var count = 0
        for (row in 0 until bounds.row) {
            for (col in 0 until bounds.col) {
                val p = Point(row, col)
                if (get(p) == 'X') {
                    count += test(p, Point::n, row >= 3)
                    count += test(p, Point::w, col >= 3)
                    count += test(p, Point::s, row < bounds.row - 3)
                    count += test(p, Point::e, col < bounds.col - 3)
                    count += test(p, Point::nw, row >= 3 && col >= 3)
                    count += test(p, Point::ne, row >= 3 && col < bounds.col - 3)
                    count += test(p, Point::sw, row < bounds.row - 3 && col >= 3)
                    count += test(p, Point::se, row < bounds.row - 3 && col < bounds.col - 3)
                }
            }
        }
        return count
    }

    fun walk2(): Int {
        var count = 0
        for (row in 1 until bounds.row) {
            for (col in 1 until bounds.col) {
                val p = Point(row, col)
                if (get(p) == 'A') {
                    count += testMAS(p)
                }
            }
        }
        return count
    }
}

fun main() {
    val day04 = Day04()
    val grid = day04.readFile("src/main/data/day04.txt")

    println("Day 04:")
    println("Part 1: ${grid.walk()}")
    println("Part 2: ${grid.walk2()}")
}