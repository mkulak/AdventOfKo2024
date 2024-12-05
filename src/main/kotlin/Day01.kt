import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("Day01.txt").readLines()
    val (first, second) = input
        .map { line -> line.split("   ") }
        .map { (a, b) -> a.toInt() to b.toInt() }
        .unzip()
    val difference = first.sorted().zip(second.sorted()).sumOf { (a, b) -> abs(a - b) }
    val similarity = first.sumOf { a -> a * second.count { it == a } }
    println("Part 1: $difference")
    println("Part 2: $similarity")
}


