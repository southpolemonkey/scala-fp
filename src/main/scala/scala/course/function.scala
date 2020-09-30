package scala.course

object function extends App {

  def aFunction(a:String, b:String): String = {
    a + " " + b
  }

  println(aFunction("Hello", "world"))


  def fib1(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib1(n-1) + fib1(n-2)
  }

  val fib_list = for (n <- 0 to 10 ) yield fib1(n)
  println(fib_list)

  // construct a infinite fibonacci sequence
  var inf_fib = List(0, 1)
  val a = 0
  val b = 1

//  while (true) {
//    val a, b = b, a + b
//    inf_fib = inf_fib :+ (a+b)
//  }

  /*
    recursive functions
    1. fibonacci number
    2. factorial
    3. test if a number is prime
   */

  def factorial(n: Int): Int = {
    if (n == 1) {return 1}
    else {return n * factorial(n - 1)}
  }

  println(s"5! equals to ${factorial(5)}")

  def fib(n: Int): Int = {
    if (n <= 2) 1
    else fib(n-1) + fib(n-2)
  }

  println(s"fibonacci number of 8 is ${fib(8)}")

  def isPrime(n: Int): Boolean={
    def isPrimeuntil(t:Int): Boolean = {
      if ( t<=1) true
      else n % t != 0 && isPrimeuntil(t - 1)
    }

    isPrimeuntil(n / 2)
  }

  println(isPrime(9))
  println(isPrime(13579))

}
