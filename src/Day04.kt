fun main() {
  fun part1(input: List<String>): Int {
    return input.filter { it.doesContainEachOther() }.size
  }

  fun part2(input: List<String>): Int {
    return input.filter { it.hasOverlap() }.size
  }

  val testInput = readInput("Day04.txt")
  println(part1(testInput))
  println(part2(testInput))
}

private fun String.doesContainEachOther(): Boolean {
  val commaSplit = split(",")
  val first = commaSplit[0].split("-").let {
    it[0].toInt() .. it[1].toInt()
  }
  val second = commaSplit[1].split("-").let {
    it[0].toInt() .. it[1].toInt()
  }
  return (first.first in second && first.last in second) || (second.first in first && second.last in first)
}

private fun String.hasOverlap(): Boolean {
  val commaSplit = split(",")
  val first = commaSplit[0].split("-").let {
    it[0].toInt() .. it[1].toInt()
  }
  val second = commaSplit[1].split("-").let {
    it[0].toInt() .. it[1].toInt()
  }

  return first.first in second || first.last in second || second.first in first || second.last in first
}