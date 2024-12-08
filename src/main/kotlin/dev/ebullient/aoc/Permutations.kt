package dev.ebullient.aoc

// Extension function
fun <T> List<T>.pairs(): List<Pair<T, T>> {
    if (isEmpty() || size == 1) return emptyList()

    // Use flatMapIndexed to iterate over the list with indices
    // For each element, create pairs with all subsequent elements
    return flatMapIndexed { i, a ->
        // Create pairs of the current element with each subsequent element
        subList(i + 1, size).map { b -> a to b }
    }
}
