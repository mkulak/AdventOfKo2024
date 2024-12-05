fun main() {
    val lines = readInput("Day05")
    val ordering = HashMap<Int, MutableSet<Int>>()
    lines.takeWhile { it.isNotEmpty() }.map { it.split("|") }.forEach { (a, b) ->
        ordering.getOrPut(b.toInt(), ::HashSet) += a.toInt()
    }
    val updates = lines.takeLastWhile { it.isNotEmpty() }.map { it.split(",").map(String::toInt) }
    val (valid, invalid) = updates.partition { isValid(it, ordering) }
    val part1 = valid.sumOf { it[it.size / 2] }
    val part2 = invalid.map { fix(it, ordering) }.sumOf { it[it.size / 2] }
    println(part1)
    println(part2)
}

fun isValid(update: List<Int>, ordering: Map<Int, Set<Int>>): Boolean {
    val all = update.toSet()
    val seen = HashSet<Int>()
    return update.all { e ->
        seen += e
        ordering[e].orEmpty().all { it in seen || it !in all }
    }
}

fun fix(update: List<Int>, ordering: Map<Int, Set<Int>>): List<Int> {
    val all = update.toMutableSet()
    val res = LinkedHashSet<Int>()
    while (all.isNotEmpty()) {
        val e = all.find { e -> ordering[e].orEmpty().all { it in res || it !in all } }!!
        all -= e
        res += e
    }
    return res.toList()
}
