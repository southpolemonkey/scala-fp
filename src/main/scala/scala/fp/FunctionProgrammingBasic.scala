package scala.fp

object FunctionProgrammingBasic extends App {

  // USE function as Object
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("5") + 10)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  println(adder(1, 3))

  // Function1[Int, Function1[Int, Int]]
  val transform: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x * y
    }
  }

  println(transform.apply(5).apply(6)) // currying function
  println(transform(5)(6))
}
