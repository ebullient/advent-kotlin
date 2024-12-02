package dev.ebullient.aoc

import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

abstract class Day {
    val spaces: Pattern = Pattern.compile("\\s+");

    fun toLines(input: String): List<String> {
        return input.split("\n");
    }

    fun fileToLines(fileName: String = ""): List<String> {
        return Files.readAllLines(Path.of(fileName));
    }
}