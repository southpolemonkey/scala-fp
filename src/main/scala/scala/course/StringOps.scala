package scala.course

object StringOps extends App {

  val str: String = "Some random string for testing"

  println(str.toUpperCase)
  println(str.substring(0, 7))
  println(str.split(" ").toList)
  println(str.startsWith("Some"))
  println(str.length)

  val aNumString = "45"
  println(aNumString.toInt)
  println("a" +: aNumString :+ "z")

  // s-interpolation
  val fName = "Alex"
  val lName = "Rong"
  println(s"my name is $fName $lName")

  // f-interpolation
  val speed = 6f
  val myth = f"$fName%s runs $speed%2.2fkm/h"
  println(myth)

  // raw-interpolation
  println("escape\nbackslash")
  println(raw"escape\nbackslash")
}
