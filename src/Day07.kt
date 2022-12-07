fun main() {
  fun part1(lines: List<String>): Long {
    val dirs = mutableListOf(Dir(null))
    var current: Dir = dirs.first()

    lines.drop(1).forEach { line ->
      when {
        line == "$ cd .." -> current = current.parent!!
        line.startsWith("$ cd") -> { Dir(current).also { current.children += it; dirs += it; current = it} }
        line[0].isDigit() -> current.filesSize += line.substringBefore(" ").toLong()
      }
    }

    return dirs.filter { it.totalSize <= 100_000 }.sumOf { it.totalSize }
  }


  fun part2(lines: List<String>): Long {
    val dirs = mutableListOf(Dir(null))
    var current: Dir = dirs.first()

    lines.drop(1).forEach { line ->
      when {
        line == "$ cd .." -> current = current.parent!!
        line.startsWith("$ cd") -> { Dir(current).also { current.children += it; dirs += it; current = it} }
        line[0].isDigit() -> current.filesSize += line.substringBefore(" ").toLong()
      }
    }

    val missing = 30_000_000 - (70_000_000 - dirs.first().totalSize)

    return dirs.filter { it.totalSize >= missing }.minOf { it.totalSize }
  }

  val testInput = readInput("Day07.txt")
  println(part1(testInput))
  println(part2(testInput))
}

class Dir(val parent: Dir?, var children: List<Dir> = emptyList()) {
  var filesSize = 0L
  val totalSize: Long get() = filesSize + children.sumOf { it.totalSize }
}