package scala.course

import java.util.concurrent.atomic.DoubleAccumulator

import scala.annotation.tailrec

object recursion extends App {
  /*
    more examples on recursion: https://alvinalexander.com/scala/scala-recursion-examples-recursive-programming/
   */

  @tailrec
  def factorial(n:Int, accumulator: BigInt):BigInt = {
    if (n <= 1) return 1
    else factorial(n-1, n * accumulator)
  }

  println(factorial(5, 1))

  def concatString(aString: String, n: Int): String = {
    def concatStringHelper(aString: String, n: Int, accumulator: String): String = {
      if (n <= 0) return accumulator
      else concatStringHelper(aString, n - 1, aString + accumulator)
    }

    val result = concatStringHelper(aString, n, "")
    return result
  }

  println(concatString("Scala+", 3))

  def fib(n: Int): Int = {
    def fibTailRec(t: Int, last: Int, nextLast: Int): Int = {
      println(s"Calculating next number for $last and $nextLast and t is $t")
      if (t >= n) return last
      else fibTailRec(t + 1, last + nextLast, last)
    }
    println(s"Computing fibnacci number for $n")
    if (n <= 2) return 1
    else fibTailRec(2, 1, 1)
  }

  println(fib(8))

  def fibSeq(n: Int): Array[Int] = {
    var result = Array(1, 1)
    var t = 3
    var last = 1
    var nextLast = 1
    while (t <= n) {
      val next = last + nextLast
      nextLast = last
      last = next
      result = result :+ next
      t = t + 1
    }
    return result
  }

  // println(fibSeq(5)) gives [I@49e4cb85, the garbage object
  println(fibSeq(5).mkString(","))

}


