fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { it.commonChar().priority() }
  }

  fun part2(input: List<String>): Int {
    return input.chunked(3).sumOf { it.commonChar().priority() }
  }

  val testInput = readInput("Day03.txt")
  println(part1(testInput))
  println(part2(testInput))
}

private fun String.commonChar(): Char {
  val length     = length
  val firstPart  = take(length / 2)
  val secondPart = takeLast(length / 2)

  return firstPart.find { secondPart.contains(it) }!!
}

private fun  List<String>.commonChar() = get(0).find { get(1).contains(it) && get(2).contains(it) }!!

val chars = ('a'..'z') + ('A'..'Z')

private fun Char.priority(): Int = chars.indexOf(this) + 1