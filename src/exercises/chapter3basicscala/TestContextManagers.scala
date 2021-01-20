package exercises.chapter3basicscala

import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter, IOException}

object TestContextManagers {
  def main(args: Array[String]) = {
    withFileWriter("File.txt") {
      writer => writer.write("Hello\n")
        writer.write("World!")
    }

    val result: String = withFileReader("File.txt") {
      reader => reader.readLine() + "\n" + reader.readLine()
    }

    assert(result == "Hello\nWorld!")
  }

  def withFileWriter(filename: String) (callback: BufferedWriter => Unit) = {
    val writer: BufferedWriter = new BufferedWriter(new FileWriter(filename))
    try {
      callback(writer)
    } finally {
      writer.close()
    }
  }

  def withFileReader(filename: String) (callback: BufferedReader => String): String = {
    val reader: BufferedReader = new BufferedReader(new FileReader(filename))
    try {
      callback(reader)
    } catch {
      case msg: IOException => "Reached EOF!"
    } finally {
      if (reader != null) {
        reader.close()
      }
    }
  }
}
