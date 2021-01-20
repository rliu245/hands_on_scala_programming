package exercises.chapter3basicscala

import scala.collection.immutable.Range

object FlexibleFizzBuzz {
  def main(args: Array[String]): Unit = {
    // Test1: choose to ignore the output
    run(15){s => {}}

    // Test2: choose to println output directly
    run(15){s => println(s)}

    // Test3: choose to store output into an array (can be optimized for more flexibility where array.size != num)
    val output = new Array[String](15)
    var i: Int = 0
    run(15){s =>
      output(i) = s
      i += 1
    }
    println(output.toList)
  }

  def getFizzBuzz(num: Int): String = {
    val divisibleByThree: Boolean = if (num % 3 == 0) true else false
    val divisibleByFive: Boolean = if (num % 5 == 0) true else false

    if (divisibleByThree && divisibleByFive) "FizzBuzz"
    else if (divisibleByThree) "Fizz"
    else if (divisibleByFive) "Buzz"
    else num.toString()
  }

  def run(num: Int) (callback: String => Unit) = {
    /*
     1) choose to ignore the output
     2) println output directly
     3) store the output in a previously allocated array
     */

    for (i <- Range(1, num + 1)) {
      callback(getFizzBuzz(i))
    }
  }
}
