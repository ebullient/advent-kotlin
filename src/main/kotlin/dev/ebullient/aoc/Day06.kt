package dev.ebullient.aoc

class Day06 : Day() {
    fun readString(input: String): GuardField {
        return GuardField(toLines(input))
    }

    fun readFile(fileName: String = ""): GuardField {
        return GuardField(fileToLines(fileName))
    }
}

data class Walk(val direction: Char, val next: Char, val step: (Point) -> Point)

class GuardField(input: List<String>) : Grid() {
    lateinit var start: Point

    init {
        build(input)
    }

    override fun previsit(p: Point, v: Char): Boolean {
        if (v == '^') {
            start = p
        }
        return v != '.'
    }

    private val directions = mapOf(
        '^' to Walk('^', '>', Point::n),
        '>' to Walk('>', 'v', Point::e),
        'v' to Walk('v', '<', Point::s),
        '<' to Walk('<', '^', Point::w)
    )

    private fun turn(walk: Walk): Walk {
        return directions[walk.next] ?: walk
    }

    fun patrol(): Int {
        val path: MutableMap<Point, String> = mutableMapOf()
        return patrol(path)
    }

    private fun patrol(path: MutableMap<Point, String>): Int {
        var steps = 1
        var pos = start

        var walk = directions[get(start)] ?: return steps
        append(path, start, walk.direction)

        while(true) {
            val next = walk.step(pos)
            if (!inbounds(next)) {
                break
            }
            val c = peek(path, next)
            if (c == '#' || c == 'O') {
                walk = turn(walk)
                append(path, pos, '+')
            } else {
                if (c == ' ') {
                    path[pos] = walk.direction.toString()
                    steps++
                } else if (cycle(path, next, walk.direction)) {
                    steps = -1
                    break
                } else {
                    append(path, next, walk.direction)
                }
                pos = next
            }
        }
        return steps
    }

    private fun peek(path: Map<Point, String>, pos: Point): Char {
        val visits = path[pos] ?: get(pos).toString()
        return visits.last()
    }

    private fun cycle(path: Map<Point, String>, pos: Point, dir: Char): Boolean {
        val visits = path[pos] ?: get(pos).toString()
        return visits.contains(dir)
    }

    private fun append(path: MutableMap<Point, String>, pos: Point, dir: Char): String {
        val visits = (path[pos] ?: get(pos).toString()) + dir
        path[pos] = visits
        return visits
    }

    fun obstruct(): Int {
        var count = 0
        for (row in 0 until bounds.row) {
            for (col in 0 until bounds.col) {
                val path: MutableMap<Point, String> = mutableMapOf()
                val p = Point(row, col)
                if (get(p) == ' ') {
                    path[p] = "O"
                    if (patrol(path) < 0) {
                        count++
                    }
                }
            }
        }
        return count
    }
}

fun main() {
    val day06 = Day06()
    val guardField = day06.readFile("src/main/data/day06.txt")

    println("Day 06:")
    val begin1 = System.currentTimeMillis()
    println("Part 1: ${guardField.patrol()}")
    println("Time: ${System.currentTimeMillis() - begin1}ms")

    val begin2 = System.currentTimeMillis()
    println("Part 2: ${guardField.obstruct()}")
    println("Time: ${System.currentTimeMillis() - begin2}ms")
}