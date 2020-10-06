package pf

object PartialFunctions  extends App {
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(2))
  //  println(aPartialFunction(57273))

  // PF utilities
  println(aPartialFunction.isDefinedAt(67))

  // lift --> transform partial to total function
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(98))

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  val aTotalFunction: Int => Option[Int] = {
    case 1 => Some(99)
    case _ => None
  }

  println(aTotalFunction(10))

  // HOFs accept partial functions as well
  val aMappedList = List(1,2,3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  val chatbot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is HAL9000"
    case "goodbye" => "Once you start talking to me, there is no return, human!"
    case "call mom" => "Unable to find your phone without your credit card"
    case "exit" => sys.exit(1)
    case _ => "No idea the response"
  }

  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)


  val xs = 1 to 10 by 2
  val ys = 1 to 10 by 3

  val aa = for (x <- xs; y <- ys) println(x * y)
  val bb = for (
    x <- xs;
    y <- ys
  ) yield x * y
}
