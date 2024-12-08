package dev.ebullient.aoc

class Day08 : Day() {
    fun readString(input: String): AntennaMap {
        return AntennaMap(toLines(input))
    }

    fun readFile(fileName: String): AntennaMap {
        return AntennaMap(fileToLines(fileName))
    }

    fun findAntiNodes(map: AntennaMap): Pair<Int, Map<Point, Char>> {
        var count = 0
        val antiNodes: MutableMap<Point, Char> = mutableMapOf()
        for(points in map.antennas.values) {
            val permutations = points.pairs()
            for ((a, b) in permutations) {
                val difference = a.difference(b)
                val node1 = b.add(difference)
                val node2 = a.subtract(difference)
                count += testAntiNode(node1, map, antiNodes)
                count += testAntiNode(node2, map, antiNodes)
            }
        }
        return Pair(count, antiNodes)
    }

    fun findAllAntiNodes(map: AntennaMap): Pair<Int, Map<Point, Char>> {
        var count = 0
        val antiNodes: MutableMap<Point, Char> = mutableMapOf()
        for(points in map.antennas.values) {
            val permutations = points.pairs()
            for ((a, b) in permutations) {
                count += testAllAntiNodes(a, a.difference(b), map, antiNodes)
                count += testAllAntiNodes(b, b.difference(a), map, antiNodes)
            }
        }
        return Pair(count, antiNodes)
    }

    private fun testAntiNode(p: Point, map: AntennaMap, antiNodes: MutableMap<Point, Char>): Int {
        if (!map.inbounds(p)) {
            return 0
        }
        when (antiNodes[p] ?: '.') {
            '.' -> {
                antiNodes[p] = '#'
                return 1
            }
            else -> return 0
        }
    }

    private fun testAllAntiNodes(p: Point, diff: Point, map: AntennaMap, antiNodes: MutableMap<Point, Char>): Int {
        var count = 0
        var node = p
        while (map.inbounds(node)) {
            count += testAntiNode(node, map, antiNodes)
            node = node.add(diff)
        }
        return count
    }
}

class AntennaMap(input: List<String>) : Grid() {
    val antennas: MutableMap<Char, MutableList<Point>> = mutableMapOf()

    init {
        build(input)
    }

    override fun previsit(p: Point, v: Char): Boolean {
        when(".#".contains(v)) {
            true -> return false
            false -> {
                antennas.computeIfAbsent(v) { mutableListOf() }.add(p)
                return true
            }
        }
    }
}

fun main() {
    val day08 = Day08()
    val map = day08.readFile("src/main/data/day08.txt")

    println("Day 08:")
    val (count, _) = day08.findAntiNodes(map)
    println("Part1: $count")
    val (count2, _) = day08.findAllAntiNodes(map)
    println("Part1: $count2")
}