fun main() {
    val lines = readInput("Day05")
    val ordering = HashMap<Int, MutableSet<Int>>()
    lines.takeWhile { it.isNotEmpty() }.forEach {
        val (a, b) = it.split("|")
        ordering.getOrPut(b.toInt(), ::HashSet) += a.toInt()
    }
    val updates = lines.takeLastWhile { it.isNotEmpty() }.map {
        it.split(",").map(String::toInt)
    }
    val (valid, invalid) = updates.partition { isValid(it, ordering) }
    val part1 = valid.sumOf { it[it.size / 2] }
    val part2 = invalid.map { fix(it, ordering) }.sumOf { it[it.size / 2] }
    println(part1)
    println(part2)
}

fun isValid(update: List<Int>, ordering: Map<Int, Set<Int>>): Boolean {
    val all = update.toSet()
    val seen = HashSet<Int>()
    return update.all { u ->
        val required = ordering[u] ?: emptySet()
        seen += u
        required.all { it in seen || it !in all }
    }
}

fun fix(update: List<Int>, ordering: Map<Int, Set<Int>>): List<Int> {
    val all = update.toMutableSet()
    val localOrdering = ordering.filterKeys { it in all }.mapValues { (_, required) -> required.filter { it in all } }
    val res = LinkedHashSet<Int>()
    while (all.isNotEmpty()) {
        val e = all.find { e -> localOrdering[e].orEmpty().all { it in res } }!!
        all -= e
        res += e
    }
    return res.toList()
}

//5248
//4507


