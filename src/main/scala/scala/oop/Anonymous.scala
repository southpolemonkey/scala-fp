package scala.oop

object Anonymous extends App {
  abstract class Animal {
    def eat: Unit
  }

  val aAnimal: Animal = new Animal {
    override def eat: Unit = println("eat something")
  }

  println(aAnimal.getClass)
}
