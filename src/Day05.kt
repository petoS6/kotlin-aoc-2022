fun main() {
  fun part1(input: List<String>): String {
    input.moves().forEach { move ->
      val crate = crates[move.second].takeLast(move.first).reversed()
      repeat(move.first) { crates[move.second].removeLast() }
      crates[move.third] = crates[move.third].apply { addAll(crate) }
    }

    return crates.joinToString("") { it.last() }
  }


  fun part2(input: List<String>): String {
    input.moves().forEach { move ->
      val crate = crates2[move.second].takeLast(move.first)
      repeat(move.first) { crates2[move.second].removeLast() }
      crates2[move.third] = crates2[move.third].apply { addAll(crate) }
    }

    return crates2.joinToString("") { it.last() }
  }

  val testInput = readInput("Day05.txt")
  println(part1(testInput))
  println(part2(testInput))
}

private val crates = mutableListOf(
  mutableListOf("Q","M","G","C","L"),
  mutableListOf("R","D","L","C","T","F","H","G"),
  mutableListOf("V","J","F","N","M","T","W","R"),
  mutableListOf("J","F","D","V","Q","P"),
  mutableListOf("N","F","M","S","L","B","T"),
  mutableListOf("R","N","V","H","C","D","P"),
  mutableListOf("H","C","T"),
  mutableListOf("G","S","J","V","Z","N","H","P"),
  mutableListOf("Z","F","H","G")
)

private val crates2 = mutableListOf(
  mutableListOf("Q","M","G","C","L"),
  mutableListOf("R","D","L","C","T","F","H","G"),
  mutableListOf("V","J","F","N","M","T","W","R"),
  mutableListOf("J","F","D","V","Q","P"),
  mutableListOf("N","F","M","S","L","B","T"),
  mutableListOf("R","N","V","H","C","D","P"),
  mutableListOf("H","C","T"),
  mutableListOf("G","S","J","V","Z","N","H","P"),
  mutableListOf("Z","F","H","G")
)

private fun List<String>.moves(): List<Triple<Int, Int, Int>> {
  return drop(10).map {
    val splitted = it.split(" ")
    Triple(splitted[1].toInt(), splitted[3].toInt() - 1, splitted[5].toInt() - 1)
  }
}