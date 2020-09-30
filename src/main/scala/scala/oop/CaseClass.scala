package scala.oop

object CaseClass extends App {

  case class Person(name: String, age: Int)
  val alex = new Person("alex", 28)
  println(alex.name)
  println(alex)
  println(alex.toString)

  // equals and hashcode
  val alexrong = new Person("alex", 28)
  println(alex == alexrong)

  // copy method
  val oldalex = alex.copy(age=35)
  println(oldalex.age)

  // caseclass has companion objects

  // serializable

  // CCs have extractor patterns ==> used in pattern matching

  case object UK {
    def name: String = "United Kingdom of Great Britain and North Ireland"
  }

  println(UK.name)

}
