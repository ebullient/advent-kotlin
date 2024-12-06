package dev.ebullient.aoc.kotlin

open class Grid(input: List<String>) {
    val data: Map<Point, Char>
    val numRows: Int
    val numCols: Int

    init {
        val data = mutableMapOf<Point, Char>()
        input.forEachIndexed { row, r ->
            r.forEachIndexed { col, v ->
                val pt = Point(row, col)
                if (initVisit(pt, v)) {
                    data[pt] = v
                }
            }
        }
        this.data = data
        this.numRows = input.size
        this.numCols = input[0].length
    }

    open fun initVisit(p: Point, v: Char): Boolean {
        return true
    }

    fun get(p: Point): Char {
        return data[p] ?: ' '
    }

    fun inbounds(p: Point): Boolean {
        return p.row in 0 until numRows && p.col in 0 until numCols
    }

    fun print() {
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                print(get(Point(row, col)))
            }
            println()
        }
    }
}
