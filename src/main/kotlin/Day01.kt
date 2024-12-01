import java.util.HashMap
import kotlin.math.absoluteValue

fun main() {
    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun parse(input: List<String>) = input.map { line ->
    val (a, b) = line.split("   ")
    a.toInt() to b.toInt()
}

private fun part1(input: List<String>): Int {
    val (first, second) = parse(input).unzip()
    return first.sorted().zip(second.sorted()).sumOf { (a, b) -> (b - a).absoluteValue }
}

fun part2(input: List<String>): Int {
    val (first, second) = parse(input).unzip()
    val freq = HashMap<Int, Int>()
    second.forEach { b -> freq.compute(b) { _, cur -> (cur ?: 0) + 1 } }
    return first.sumOf { it * freq.getOrDefault(it, 0) }
}