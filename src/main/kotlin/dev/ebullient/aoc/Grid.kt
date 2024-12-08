package dev.ebullient.aoc

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

    fun build(input: List<String>) {
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
    }

    open fun prebuild() {}

    open fun previsit(p: Point, v: Char): Boolean {
        return true
    }

    fun get(p: Point): Char {
        return data[p] ?: ' '
    }

    fun get(p: Point, orElse: Char): Char {
        return data[p] ?: orElse
    }

    fun inbounds(p: Point): Boolean {
        return p.row in 0 until bounds.row && p.col in 0 until bounds.col
    }

    fun draw(placeholder: Char) {
        data.draw(placeholder, bounds)
    }
}
