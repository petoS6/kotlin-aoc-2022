fun main() {
  fun part1(lines: List<String>): Int {
    var x = 1
    var cycle = 1
    var sum = 0

    fun processCycle() {
      if (cycle % 40 == 20) sum += cycle * x
      cycle++
    }

    lines.forEach { line ->
      if (line == "noop") {
        processCycle()
      } else {
        processCycle()
        processCycle()
        x += line.substringAfter(" ").toInt()
      }
    }

    return sum
  }

  fun part2(lines: List<String>): Unit {
    var x = 1
    var cycle = 1
    val screen = mutableMapOf<Point, Boolean>()

    fun processCycle() {
      val screenX = (cycle - 1) % 40
      if (screenX in (x - 1)..(x + 1)) {
        screen[Point(screenX, (cycle - 1) / 40)] = true
      }
      cycle++
    }

    fun Map<Point, Boolean>.printArea() {
      (0..6).forEach { y ->
        (0..40).forEach { x ->
          if (this[Point(x, y)] == true )print("#") else print(" ")
        }
        println()
      }
    }

    lines.forEach { line ->
      if (line == "noop") {
        processCycle()
      } else {
        processCycle()
        processCycle()
        x += line.substringAfter(" ").toInt()
      }
    }

    screen.printArea()
  }

  val testInput = readInput("Day10.txt")
  println(part1(testInput))
  println(part2(testInput))
}

data class Point(val x: Int, val y: Int)