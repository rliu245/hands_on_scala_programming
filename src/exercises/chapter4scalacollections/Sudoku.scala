package exercises.chapter4scalacollections

import scala.collection.mutable.StringBuilder

object Sudoku {
  def isValidSudoku(grid: Array[Array[Int]]): Boolean = {
    !Range(0, 9).exists{ i =>
      val row = Range(0, 9).map(grid(i)(_)).filter(num => num != 0)
      val col = Range(0, 9).map(grid(_)(i)).filter(num => num != 0)
      val square = Range(0, 9).map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3)).filter(num => num != 0)
      row.distinct.length != row.length ||
        col.distinct.length != col.length ||
        square.distinct.length != square.length
    }
  }

  def renderSudoku(grid: Array[Array[Int]]): String = {
    /*
     PATTERNS:
     1) rows 0, 4, and 8 use "+-------+-------+-------+"
     2) every row that isn't in (0, 4, 8) begins with |
     3) following #2, every row is in groups of 3 and each group ends with |
     */
    var output: StringBuilder = new StringBuilder()
    val rowLines: String = "+-------+-------+-------+\n"

    for {(counter, row) <- (0 to grid.size) zip (grid)} {
      if (counter % 3 == 0) output = output.append(rowLines)

      // every line aside from rows in (0, 4, 8) should be of the following format: "| 0 0 0 | 0 0 0 | 0 0 0 |"
      output = output.append("|")
        .append(row.grouped(3).map(_.mkString(" ", " ", " ")).mkString("|")) // Array(1, 2, 3, 4, 5, 6, 7, 8, 9) == ("1 2 3") ("4 5 6") ("7 8 9") and then " 1 2 3 | 4 5 6 | 7 8 9 "
        .append("|")
        .append("\n")
    }
    output.append(rowLines).toString
  }

  def main(args: Array[String]): Unit = {
    assert(isValidSudoku(Array(
      Array(5, 3, 4,  6, 7, 8,  9, 1, 2),
      Array(6, 7, 2,  1, 9, 5,  3, 4, 8),
      Array(1, 9, 8,  3, 4, 2,  5, 6, 7),

      Array(8, 5, 9,  7, 6, 1,  4, 2, 3),
      Array(4, 2, 6,  8, 5, 3,  7, 9, 1),
      Array(7, 1, 3,  9, 2, 4,  8, 5, 6),

      Array(9, 6, 1,  5, 3, 7,  2, 8, 4),
      Array(2, 8, 7,  4, 1, 9,  6, 3, 5),
      Array(3, 4, 5,  2, 8, 6,  1, 7, 9),
    )))

    assert(isValidSudoku(Array(
      Array(5, 3, 4,  6, 7, 8,  9, 1, 2),
      Array(6, 7, 2,  1, 9, 5,  3, 4, 8),
      Array(1, 9, 8,  3, 4, 2,  5, 6, 7),

      Array(8, 5, 9,  0, 6, 0,  0, 2, 3),
      Array(4, 2, 6,  8, 5, 3,  0, 0, 1),
      Array(7, 1, 3,  0, 2, 0,  8, 0, 0),

      Array(9, 6, 0,  0, 0, 0,  2, 8, 0),
      Array(0, 0, 0,  0, 0, 0,  0, 3, 5),
      Array(0, 0, 5,  2, 0, 6,  1, 0, 0),
    )))

    assert(!isValidSudoku(Array(
      Array(5, 3, 4,  6, 7, 8,  9, 1, 6),
      Array(6, 7, 2,  1, 9, 5,  3, 4, 8),
      Array(1, 9, 8,  3, 4, 2,  5, 6, 7),

      Array(8, 5, 9,  0, 6, 0,  0, 2, 3),
      Array(4, 2, 6,  8, 5, 3,  0, 0, 1),
      Array(7, 1, 3,  0, 2, 0,  8, 0, 0),

      Array(9, 6, 0,  0, 0, 0,  2, 8, 0),
      Array(0, 0, 0,  0, 0, 0,  0, 3, 5),
      Array(0, 0, 5,  2, 0, 6,  1, 0, 0),
    )))

    println(renderSudoku(Array(
      Array(5, 3, 4,  6, 7, 8,  9, 1, 6),
      Array(6, 7, 2,  1, 9, 5,  3, 4, 8),
      Array(1, 9, 8,  3, 4, 2,  5, 6, 7),

      Array(8, 5, 9,  0, 6, 0,  0, 2, 3),
      Array(4, 2, 6,  8, 5, 3,  0, 0, 1),
      Array(7, 1, 3,  0, 2, 0,  8, 0, 0),

      Array(9, 6, 0,  0, 0, 0,  2, 8, 0),
      Array(0, 0, 0,  0, 0, 0,  0, 3, 5),
      Array(0, 0, 5,  2, 0, 6,  1, 0, 0),
    )))
  }
}
