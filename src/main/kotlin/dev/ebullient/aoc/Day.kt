package dev.ebullient.aoc

import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

abstract class Day <T> {

    abstract fun run();

    abstract fun read(input: List<String>): T;

    fun readString(input: String): T {
        return this.read(toLines(input));
    }

    fun readFile(fileName: String = ""): T {
        return read(fileToLines(fileName));
    }

    fun toLines(input: String): List<String> {
        return input.split("\n");
    }

    fun fileToLines(fileName: String = ""): List<String> {
        return Files.readAllLines(Path.of(fileName));
    }
}