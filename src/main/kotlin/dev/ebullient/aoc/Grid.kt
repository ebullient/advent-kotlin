package dev.ebullient.aoc

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

    fun print() {
        draw(' ')
    }

    fun draw(placeholder: Char) {
        for (row in 0 until bounds.row) {
            for (col in 0 until bounds.col) {
                print(get(Point(row, col), placeholder))
            }
            println()
        }
    }
}
