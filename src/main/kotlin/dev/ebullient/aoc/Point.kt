package dev.ebullient.aoc

data class Point(val row: Int, val col: Int) {
    fun n(): Point {
        return Point(row - 1, col)
    }
    fun nw(): Point {
        return Point(row - 1, col - 1)
    }
    fun ne(): Point {
        return Point(row - 1, col + 1)
    }
    fun s(): Point {
        return Point(row + 1, col)
    }
    fun sw(): Point {
        return Point(row + 1, col - 1)
    }
    fun se(): Point {
        return Point(row + 1, col + 1)
    }
    fun w(): Point {
        return Point(row, col - 1)
    }
    fun e(): Point {
        return Point(row, col + 1)
    }

    override fun toString(): String {
        return "(r=$row, c=$col)"
    }
}