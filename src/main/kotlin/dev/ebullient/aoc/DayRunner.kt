package dev.ebullient.aoc

fun main(args: Array<String>) {
    val year = args.getOrNull(0)?.toIntOrNull() ?: 2025
    val day = args.getOrNull(1)?.toIntOrNull() ?: 1

    runDay(year, day)
}

fun runDay(year: Int, day: Int) {
    val dayStr = day.toString().padStart(2, '0')
    val className = "dev.ebullient.aoc.y$year.Day$dayStr"
    try {
        val clazz = Class.forName(className)
        val instance = clazz.getDeclaredConstructor().newInstance() as Day<*>
        instance.run()
    } catch (e: ClassNotFoundException) {
        println("Error: Could not find class $className")
        println("Make sure the class exists for year $year, day $day")
    } catch (e: Exception) {
        println("Error running day $day of year $year: ${e.message}")
        e.printStackTrace()
    }
}