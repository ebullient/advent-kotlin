package dev.ebullient.aoc

import kotlin.math.min

class Day05 : Day() {
    fun readString(input: String): PrinterUpdates {
        return read(toLines(input))
    }

    fun readFile(fileName: String = ""): PrinterUpdates {
        return read(fileToLines(fileName))
    }

    private fun read(input: List<String>): PrinterUpdates {
        val rules = mutableListOf<Rules>()
        val updates = mutableListOf<IntArray>()

        input.forEachIndexed { i, r ->
            if (r.contains("|")) {
                val (p, b) = r.split("|").map { it.trim().toInt() }
                rules.add(Rules(p, b))
            } else if (r.contains(",")) {
                val pages = r.split(",").map { it.trim().toInt() }.toIntArray()
                updates.add(pages)
            }
        }
        return PrinterUpdates(rules, updates)
    }

    fun isCorrect(pages: IntArray, rules: List<Rules>): Boolean {
        for((i, p) in pages.withIndex()) {
            for(r in rules) {
                val bi = pages.indexOf(r.before);
                if (r.page != p || bi < 0) {
                    continue
                } else if (i > bi) {
                    return false
                }
            }
        }
        return true
    }

    fun fixit(pages: IntArray, rules: List<Rules>): Boolean {
        val myRules = rules.filter({ r -> pages.contains(r.page) && pages.contains(r.before) })
        var i = 0
        var fixed = false
        while (i < pages.size) {
            val p = pages[i]
            for(r in myRules) {
                val bi = pages.indexOf(r.before);
                if (r.page != p || bi > i) {
                    continue
                }
                fixed = true
                pages[i] = r.before
                pages[bi] = p
                i = min(i, bi) - 1
                break
            }
            i++
        }

        return fixed
    }

    fun middle(pages: IntArray): Int {
        val i =  (pages.size / 2)
        return pages[i]
    }

    fun findCorrectUpdates(updates: PrinterUpdates): Int {
        var count = 0
        for (u in updates.updates) {
            if (isCorrect(u, updates.rules)) {
                count += middle(u)
            }
        }
        return count
    }

    fun fixedUpdates(updates: PrinterUpdates): Int {
        var count = 0
        for (u in updates.updates) {
            if (fixit(u, updates.rules)) {
                count += middle(u)
            }
        }
        return count
    }

}

data class Rules(val page: Int, val before: Int) {
}

data class PrinterUpdates(val rules: List<Rules>, val updates: List<IntArray>) {
}

fun main() {
    val day05 = Day05()

    println("Day 05:")
    val allUpdates = day05.readFile("src/main/data/day05.txt")
    println("Part 1: ${day05.findCorrectUpdates(allUpdates)}")
    println("Part 2: ${day05.fixedUpdates(allUpdates)}")
}