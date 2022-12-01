fun main() {
  fun part1(input: List<String>): Int {
    return getSnackSum(input).max()
  }

  fun part2(input: List<String>): Int {
    return getSnackSum(input).sortedDescending().take(3).sum()
  }

  val testInput = readInput("Day01.txt")
  println(part1(testInput))
  println(part2(testInput))
}

private fun getSnackSum(input: List<String>) = input.chunkedByPredicate(String::isBlank).map { it.sumOf(String::toInt) }

private fun <T> List<T>.chunkedByPredicate(predicate : (T) -> Boolean): List<List<T>> = buildList {
  this@chunkedByPredicate.fold(emptyList<T>()) { acc, value ->
    if (predicate(value).not()) {
      acc + value
    } else {
      add(acc)
      emptyList()
    }
  }
}
