package scala.fp

object MapFlatMap extends App {

  val numbers = List(1,2,3,4)
  val names = List('a','b', 'c', 'd')
  val colours = List("red", "blue", "green")

  val zipped: List[(Int, Char)] = numbers zip names
  println(zipped.unzip)

  val combinations = numbers.flatMap(n => names.map(c => "" + c + n))

  println(combinations)

  val forCombinations = for {
    n <- numbers
    c <- names
    b <- colours
  } yield "" + n + c + "-" + b

  println(forCombinations.length)

}
