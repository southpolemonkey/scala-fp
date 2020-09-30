package scala.oop

object ExceptionHandling extends App {
  val x: String = null

  // 1. throw an Exception
  // same as raise in python
  // throw new NullPointerException

  // 2. try catch block
  // throwable classes extend the Throwable class
  // Exception and Error

  val potentialFail = try {
    println(x.length)
  } catch {
    case e: NullPointerException => println("Null value catched")
  } finally {
    println("end process...")
  }

  // 3. define own exception

  /*
    Exercise
    1. OutOfMemoryError
    2. StackOverFlowError
    3. Implement a pocket calculator with exception
   */

//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  class OverflowException extends Exception
  class UnderflowException extends RuntimeException
  class MathCalculationException extends ArithmeticException
//  case class UnderflowException(msg: String) extends Exception
//  case class MathCalculationException(msg: String) extends Exception

  object PocketCalculator {
    def add(x:Int, y:Int):Int = {
      val result = x + y
      if (x > 0 & y > 0 & result < 0) throw new OverflowException
      else if (x < 0 & y < 0 & result > 0) throw new UnderflowException
      else result
    }

    def subtract(x:Int, y:Int):Int = {
      val result = x - y
      if (x < 0 & y > 0 & result > 0) throw new UnderflowException
      else if (x > 0 & y < 0 & result < 0) throw new OverflowException
      else result
    }

    def divide(x:Int, y:Int):Int = {
      try x / y
      catch {
        case e: ArithmeticException => throw new MathCalculationException
      }
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 10))

//  println(PocketCalculator.subtract(Int.MinValue, 10))

  println(PocketCalculator.divide(10, 0))


}


