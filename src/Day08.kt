fun main() {
  fun part1(lines: List<String>): Int {
    val matrix = lines.map {
      it.map { it.toString().toInt() }
    }

    var visible = 0
    matrix.forEachIndexed { rowIndex, row ->
      row.forEachIndexed { colIndex, tree ->
        val leftVisible   = row.filterIndexed { index, _ -> index < colIndex }.all { it < tree }
        val rightVisible  = row.filterIndexed { index, _ -> index > colIndex }.all { it < tree }
        val topVisible    = matrix.map { it[colIndex] }.filterIndexed { index, _ -> index < rowIndex }.all { it < tree }
        val bottomVisible = matrix.map { it[colIndex] }.filterIndexed { index, _ -> index > rowIndex }.all { it < tree }

        if (leftVisible || rightVisible || topVisible || bottomVisible) visible++
      }
    }

    return visible
  }


  fun part2(lines: List<String>): Int {
    val matrix = lines.map {
      it.map { it.toString().toInt() }
    }

    val scenics = mutableListOf<Int>()

    matrix.forEachIndexed { rowIndex, row ->
      row.forEachIndexed { colIndex, tree ->
        val left   = row.filterIndexed { index, _ -> index < colIndex }
        val right  = row.filterIndexed { index, _ -> index > colIndex }
        val top    = matrix.map { it[colIndex] }.filterIndexed { index, _ -> index < rowIndex }
        val bottom = matrix.map { it[colIndex] }.filterIndexed { index, _ -> index > rowIndex }

        val leftScenic   = ((left  .reversed().indexOfFirst { it >= tree }).takeIf { it != -1 }?.plus(1)) ?: left.size
        val rightScenic  = ((right .indexOfFirst { it >= tree }).takeIf { it != -1 }?.plus(1)) ?: right.size
        val topScenic    = ((top   .reversed().indexOfFirst { it >= tree }).takeIf { it != -1 }?.plus(1)) ?: top.size
        val bottomScenic = ((bottom.indexOfFirst { it >= tree }).takeIf { it != -1 }?.plus(1)) ?: bottom.size

        scenics.add(leftScenic * rightScenic * topScenic * bottomScenic)
      }
    }

    return scenics.max()
  }

  val testInput = readInput("Day08.txt")
  println(part1(testInput))
  println(part2(testInput))
}