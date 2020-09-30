package scala.oop

object Inheritance extends App {

  // single class inheritance
  // abstract class
  // interface
  // trait

  // inheritance
  sealed class Animal {
    def eat = println("eat a lot")
    def drink = println("drink nothing")
  }

  class Cat(val name: String, val breed:String ) extends Animal {
    def crunch = {
      println("crunch crunch")
    }
  }

  val marshmellow = new Cat("marshmellow", "ragdoll")
  marshmellow.crunch
  println(s"${marshmellow.name} is a ${marshmellow.breed}")

  // overiding
  class Dog extends Animal {
    override def eat: Unit = {
      super.drink
      println("dog eat bones")
    }
  }

  val dog = new Dog
  dog.eat

  // type substitution (polymorphism)
  val wildAnimal: Animal = new Dog()
  wildAnimal.eat

  // super is to call methods from parental class

  // prevent overloading
  // 1. use final on method
  // 2. use final class
  // 3. use sealed class

}
