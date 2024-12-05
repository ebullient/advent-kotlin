package dev.ebullient.aoc.kotlin

class Day04 : Day() {
    fun readString(input: String): Grid {
        return read(toLines(input))
    }

    fun readFile(fileName: String = ""): Grid {
        return read(fileToLines(fileName))
    }

    private fun read(input: List<String>): Grid {
        val data = mutableMapOf<Point, Char>()

        input.forEachIndexed { row, r ->
            r.forEachIndexed { col, v ->
                data[Point(row, col)] = v
            }
        }
        return Grid(data, input.size, input[0].length)
    }
}

data class Point(val row: Int, val col: Int)

data class Grid(
            val data: Map<Point, Char>,
            val numRows: Int,
            val numCols: Int) {

    private val xmas = "XMAS"

    fun get(p: Point): Char {
        return data[p] ?: ' '
    }

    fun n(p: Point): Point {
        return Point(p.row - 1, p.col)
    }
    fun nw(p: Point): Point {
        return Point(p.row - 1, p.col - 1)
    }
    fun ne(p: Point): Point {
        return Point(p.row - 1, p.col + 1)
    }
    fun s(p: Point): Point {
        return Point(p.row + 1, p.col)
    }
    fun sw(p: Point): Point {
        return Point(p.row + 1, p.col - 1)
    }
    fun se(p: Point): Point {
        return Point(p.row + 1, p.col + 1)
    }
    fun w(p: Point): Point {
        return Point(p.row, p.col - 1)
    }
    fun e(p: Point): Point {
        return Point(p.row, p.col + 1)
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
        val x1Top = get(nw(p))
        val x1Bot = get(se(p))
        val x2Top = get(ne(p))
        val x2Bot = get(sw(p))
        val x1ok = (x1Top == 'M' && x1Bot == 'S') || (x1Top == 'S' && x1Bot == 'M')
        val x2ok = (x2Top == 'M' && x2Bot == 'S') || (x2Top == 'S' && x2Bot == 'M')
        return if (x1ok && x2ok) 1 else 0
    }

    fun walk(): Int {
        var count = 0
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                val p = Point(row, col)
                if (get(p) == 'X') {
                    count += test(p, ::n, row >= 3)
                    count += test(p, ::w, col >= 3)
                    count += test(p, ::s, row < numRows - 3)
                    count += test(p, ::e, col < numCols - 3)
                    count += test(p, ::nw, row >= 3 && col >= 3)
                    count += test(p, ::ne, row >= 3 && col < numCols - 3)
                    count += test(p, ::sw, row < numRows - 3 && col >= 3)
                    count += test(p, ::se, row < numRows - 3 && col < numCols - 3)
                }
            }
        }
        return count
    }

    fun walk2(): Int {
        var count = 0
        for (row in 1 until numRows) {
            for (col in 1 until numCols) {
                val p = Point(row, col)
                if (get(p) == 'A') {
                    count += testMAS(p)
                }
            }
        }
        return count
    }

    fun print() {
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                print(data[Point(row, col)])
            }
            println()
        }
    }
}

fun main() {
    val day04 = Day04()
    val grid = day04.readFile("src/main/data/day04.txt")

    println("Day 04:")
    println("Part 1: ${grid.walk()}")
    println("Part 2: ${grid.walk2()}")
}