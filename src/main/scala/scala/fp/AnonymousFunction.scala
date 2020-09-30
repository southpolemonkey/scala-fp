package scala.fp

object AnonymousFunction extends App {

  val doubler = (x: Int) => x * 2
  println(doubler(2))

  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  println(adder)
  println(adder(1, 2))

}
