fun main() {
  fun part1(input: List<String>): Int {
    return input.first().windowed(4).indexOfFirst {
      it.length == it.toSet().size
    } + 4
  }


  fun part2(input: List<String>): Int {
    return input.first().windowed(14).indexOfFirst {
      it.length == it.toSet().size
    } + 14
  }

  val testInput = readInput("Day06.txt")
  println(part1(testInput))
  println(part2(testInput))
}