fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { it.split(" ").let { Game(it[0], it[1]) }.score1 }
  }

  fun part2(input: List<String>): Int {
    return input.sumOf { it.split(" ").let { Game(it[0], it[1]) }.score2 }
  }

  val testInput = readInput("Day02.txt")
  println(part1(testInput))
  println(part2(testInput))
}

private data class Game(
  val other : String,
  val yours : String
) {
  val score1 = when {
    other == "A" && yours == "X" -> 4 // Rock Draw
    other == "A" && yours == "Y" -> 8 // Rock Win
    other == "A" && yours == "Z" -> 3 // Rock Lose
    other == "B" && yours == "X" -> 1 // Paper Lose
    other == "B" && yours == "Y" -> 5 // Paper Draw
    other == "B" && yours == "Z" -> 9 // Paper Win
    other == "C" && yours == "X" -> 7 // Scissors Win
    other == "C" && yours == "Y" -> 2 // Scissors Lose
    other == "C" && yours == "Z" -> 6 // Scissors Draw
    else -> throw IllegalStateException("Unsupported choose $other and $yours")
  }

  val score2 = when {
    other == "A" && yours == "X" -> 3 // Lose
    other == "A" && yours == "Y" -> 4 // Draw
    other == "A" && yours == "Z" -> 8 // Win
    other == "B" && yours == "X" -> 1 // Lose
    other == "B" && yours == "Y" -> 5 // Draw
    other == "B" && yours == "Z" -> 9 // Win
    other == "C" && yours == "X" -> 2 // Lose
    other == "C" && yours == "Y" -> 6 // Draw
    other == "C" && yours == "Z" -> 7 // Win
    else -> throw IllegalStateException("Unsupported choose $other and $yours")
  }
}