package dev.ebullient.aoc

import kotlin.math.max
import kotlin.math.min

// Extension function
fun <T> Map<Point, T>.draw(placeholder: Char, bounds: Point) {
    for (row in 0 until bounds.row) {
        for (col in 0 until bounds.col) {
            print(this[Point(row, col)] ?: placeholder)
        }
        println()
    }
}

fun Map<Point, Char>.toString(placeholder: Char, bounds: Point): String {
    val builder = StringBuilder()
    for (row in 0 until bounds.row) {
        for (col in 0 until bounds.col) {
            builder.append(this[Point(row, col)] ?: placeholder)
        }
        builder.appendLine()
    }
    return builder.toString()
}

fun Map<Point, Char>.toString(placeholder: Char, grid: Grid): String {
    val builder = StringBuilder()
    for (row in 0 until grid.bounds.row) {
        for (col in 0 until grid.bounds.col) {
            val pt = Point(row, col)
            val value = this[pt] ?: placeholder
            builder.append(grid.get(pt, value))
        }
        builder.appendLine()
    }
    return builder.toString()
}

open class Grid() {
    private val data: MutableMap<Point, Char> = mutableMapOf<Point, Char>()
    lateinit var bounds: Point

    fun build(input: List<String>): Grid {
        prebuild()
        input.forEachIndexed { row, r ->
            r.forEachIndexed { col, v ->
                val pt = Point(row, col)
                if (previsit(pt, v)) {
                    data[pt] = v
                }
            }
        }
        this.bounds = Point(input.size, input[0].length)
        return this
    }

    open fun prebuild() {}

    open fun previsit(p: Point, v: Char): Boolean {
        return true
    }

    fun walk(visit: (Point, Char) -> Unit) {
        data.entries.forEach { visit(it.key, it.value) }
    }

    fun get(p: Point): Char {
        return data[p] ?: ' '
    }

    operator fun set(p: Point, c: Char) {
        data[p] = c
    }

    fun get(p: Point, orElse: Char): Char {
        return data[p] ?: orElse
    }

    fun inbounds(p: Point): Boolean {
        return p.row in 0 until bounds.row && p.col in 0 until bounds.col
    }

    fun box(p: Point): List<Point> {
        val rowMin = max(0, p.row - 1)
        val colMin = max(0, p.col - 1)
        val rowMax = min(bounds.row - 1, p.row + 1)
        val colMax = min(bounds.col - 1, p.col + 1)

        val result = mutableListOf<Point>()
        for (row in rowMin..rowMax) {
            for (col in colMin..colMax) {
                if (row == p.row && col == p.col) {
                    continue
                }
                result.add(Point(row, col))
            }
        }
        return result
    }

    fun draw(placeholder: Char) {
        data.draw(placeholder, bounds)
    }
}
