package scala.oop

object Notation extends App {

  // conflicting in package
  class Person(val name: String, favorMovie: String, age: Int = 0) {
    def likes(movie: String): Boolean = movie == favorMovie
    def isAlive : Boolean = true
    def unary_! : String = s"$name, unary operation"
    def unary_+ : Person = new Person(name, favorMovie, age + 1)
    def apply(): String = s"Hi try apply method"
    def apply(n: Int): String = s"$name watched $favorMovie $n times"
  }

  val alex = new Person("Alex", "Inception")
  // infix notation
  println(alex likes "Inception")

  // prefix notation
  println(!alex)
  println(+alex)

  // postfix notation
  println(alex isAlive)

  // apply
  println(alex.apply())
  println(alex()) // object is called like a function, equivalent to alex.apply()
  println(alex.apply(2))
  println(alex(10))

  /*
    1. overload the + operator

    2. Add age to the Person class
       Add a unary + operation => new person with the age + 1
       +alex => alex with the incrementer

    3. Add a "learns" method in the Person class

    4. Overload the apply method
       alex.apply(2) => "something else"
   */
}
