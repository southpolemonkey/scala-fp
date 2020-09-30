package scala.oop

/*
  https://docs.scala-lang.org/tour/generic-classes.html
  generic implementation of a stack
 */
class Stack[A] {
  private var elements: List[A] = Nil
  def push(x: A) { elements = x :: elements }
  def peek: A = elements.head
  def pop(): A = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }
}

object Stack extends App {
  val stack = new Stack[Int]
  stack.push(1)
  stack.push(2)
  println(stack.peek)
  println(stack.pop)  // prints 2
  println(stack.pop)  // prints 1

  class Fruit
  class Apple extends Fruit {
    override def toString: String = "Apple"
  }
  class Banana extends Fruit {
    override def toString: String = "Banana"
  }

  val stackFruit = new Stack[Fruit]
  val apple = new Apple
  val banana = new Banana

  stackFruit.push(apple)
  stackFruit.push(banana)
  println(stackFruit.peek.toString)
  println(stackFruit.pop().toString)
}