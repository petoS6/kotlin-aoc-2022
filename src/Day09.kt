import java.lang.Math.abs
import kotlin.math.sign

data class P(val x: Int, val y: Int)

private fun P.isCloseTo(other: P) =
  (x == other.x && y == other.y) ||
      (x == other.x + 1 && y == other.y) ||
      (x == other.x - 1 && y == other.y) ||

      (x == other.x && y == other.y + 1) ||
      (x == other.x && y == other.y - 1) ||

      (x == other.x - 1 && y == other.y - 1) ||
      (x == other.x + 1 && y == other.y + 1) ||
      (x == other.x - 1 && y == other.y + 1) ||
      (x == other.x + 1 && y == other.y - 1)

fun main() {
  fun part1(lines: List<String>): Int {
    val points = mutableListOf<P>()
    points.add(P(0, 4))

    var h = P(0, 4)
    var t = P(0, 4)

    lines.forEach { line ->
      val splitted  = line.split(" ")
      val direction = splitted[0]
      val count     = splitted[1].toInt()

      repeat(count) {
        when(direction) {
          "R" -> {
            h = h.copy(x = h.x + 1)
            if (t.isCloseTo(h).not()) t = h.copy(x = h.x - 1)
          }

          "L" -> {
            h = h.copy(x = h.x - 1)
            if (t.isCloseTo(h).not()) t = h.copy(x = h.x + 1)
          }

          "U" -> {
            h = h.copy(y = h.y - 1)
            if (t.isCloseTo(h).not()) t = h.copy(y = h.y + 1)
          }

          "D" -> {
            h = h.copy(y = h.y + 1)
            if (t.isCloseTo(h).not()) t = h.copy(y = h.y - 1)
          }
        }

        points.add(t)
      }
    }

    return points.distinct().size
  }

  fun part2(lines: List<String>) = rope(lines, 10)

  val testInput = readInput("Day09.txt")
  println(part1(testInput))
  println(part2(testInput))
}

fun move(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> =
  if(abs(head.first - tail.first) > 1 || abs(head.second - tail.second) > 1) {
    val tailX = if (head.first == tail.first) 0 else (head.first - tail.first).sign
    val tailY = if (head.second == tail.second) 0 else (head.second - tail.second).sign

    tail.first + tailX to tail.second + tailY
  } else tail

fun rope(lines: List<String>, length: Int) : Int {
  val rope = Array(length) { 0 to 0}
  val tailVisited = mutableSetOf<Pair<Int, Int>>()
  tailVisited.add(rope.last())

  for (instr in lines) {
    val (dir: String, dist: String) = instr.split(" ")
    val step = when (dir) {
      "R" -> 1 to 0
      "L" -> -1 to 0
      "U" -> 0 to 1
      "D" -> 0 to -1
      else -> error("Unknown")
    }

    repeat(dist.toInt()) {
      rope[0] = rope[0].first + step.first to rope[0].second + step.second
      for (i in 0 until rope.lastIndex) {
        rope[i + 1] = move(rope[i], rope[i+1])
      }
      tailVisited += rope.last()
    }
  }

  return tailVisited.size
}