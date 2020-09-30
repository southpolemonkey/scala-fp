package scala.oop
import course.function

import java.util.Date
import java.sql.{Date => SqlDate}
object PackageAndImports extends App {

  // 1. package members are accessible by simple name
  val stack = new Stack[Int]

  println(function.factorial(5))

  // 2. package object - one per package

  // 3. Distinguish duplicated method name
  val sqlDate = new SqlDate(2020, 8, 3)
  println(sqlDate)

}
