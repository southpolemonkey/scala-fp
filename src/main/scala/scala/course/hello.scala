package scala.course

object hello extends App {
  val x = 1 + 2
  println(x)

  val aCodeBlock = {
    val x= 1
    if (x > 2) "greater than 2" else "less than 2"
  }

  println(aCodeBlock)

  // if is an expression
  val aIfExpression = if (true) "true" else "false"
  println(aIfExpression)

  val someValue = 2 < 3

  val testBlock = {
    if (someValue) 239 else 246
      255
  }
  println(testBlock)
}

