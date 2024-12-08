package dev.ebullient.aoc

import kotlin.math.abs

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

    fun manhattan(b: Point): Any {
        return abs(row - b.row) + abs(col - b.col)
    }

    fun difference(b: Point): Point {
        return Point(b.row - row, b.col - col)
    }

    fun add(b: Point): Point {
        return Point(row + b.row, col + b.col)
    }

    fun subtract(b: Point): Point {
        return Point(row - b.row, col - b.col)
    }

    fun direction(b: Point): Point {
        return when {
            row > b.row -> when {
                col > b.col -> Point(-1, -1)
                col < b.col -> Point(-1, 1)
                else -> Point(-1, 0)
            }
            row < b.row -> when {
                col > b.col -> Point(1, -1)
                col < b.col -> Point(1, 1)
                else -> Point(1, 0)
            }
            else -> when {
                col > b.col -> Point(0, -1)
                col < b.col -> Point(0, 1)
                else -> Point(0, 0)
            }
        }
    }

    fun isInbounds(b: Point): Boolean {
        return row in 0 until b.row && col in 0 until b.col
    }

    override fun toString(): String {
        return "(r=$row, c=$col)"
    }
}