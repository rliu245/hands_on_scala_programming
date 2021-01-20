package exercises.chapter3basicscala

import scala.collection.mutable.HashMap

class Msg(val id: Int, val parent: Option[Int], val txt: String)

object PrintMessages {
  def main(args: Array[String]): Unit = {
    /*
     should print the following:

     #0 Hello
       #1 World
     #2 I am Cow
       #3 Hear me moo
       #4 Here I stand
       #5 I am Cow
         #6 Here me moo, moo
     */
    run(Array(
      new Msg(0, None, "Hello"),
      new Msg(1, Some(0), "World"),
      new Msg(2, None, "I am Cow"),
      new Msg(3, Some(2), "Hear me moo"),
      new Msg(4, Some(2), "Here I stand"),
      new Msg(5, Some(2), "I am Cow"),
      new Msg(6, Some(5), "Here me moo, moo")
    ))
  }

  def run(messages: Array[Msg]): Unit = {
    val lookup: HashMap[Int, Int] = new HashMap()

    for (message <- messages) {
      val id: Int = message.id
      val parent: Option[Int] = message.parent
      val txt: String = message.txt

      val numIndents: Int = parent match {
        case Some(messageParent) => lookup.getOrElse(messageParent, default = 0)
        case None => 0
      }
      lookup.put(id, numIndents + 1)
      println("\t" * numIndents + txt)
    }
  }
}
