package scala.oop

object Object extends App {
  println("Object chapter")

  // object does not receive parameters
  object Person {
    // object resembles "static" class level functionality like other language
    val N_EYES = 2

    // factory method
    def apply(mum: Person, dad: Person): Person = new Person("child")
  }
  class Person(val name: String) {
    // instance level functionality
  }
  // Companion Object

  val a = new Person("a")
  val b = new Person("b")

  val mingzhu = new Person("Mingzhu")

  val alex = Person(a, b)
  println(alex.name)

  // singleton instance by default

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit { ... }

}
