import java.util.HashMap
import kotlin.math.absoluteValue

fun main() {
    val input = readInputAsText("Day03")
    println(part1(input))
    println(part2(input))
}

private const val MUL = "mul\\((\\d{1,3}),(\\d{1,3})\\)"

private fun part1(input: String) = MUL.toRegex().findAll(input).sumOf {
    val (_, a, b) = it.groupValues
    a.toInt() * b.toInt()
}

private fun part2(input: String): Int =
    "(do\\(\\)|don't\\(\\)|$MUL)".toRegex().findAll(input).fold(0 to true) { (acc, enabled), res ->
        when {
            res.groupValues.first() == "do()" -> acc to true
            res.groupValues.first() == "don't()" || !enabled -> acc to false
            else -> {
                val (_, _, a, b) = res.groupValues
                acc + (a.toInt() * b.toInt()) to true
            }
        }
    }.first

