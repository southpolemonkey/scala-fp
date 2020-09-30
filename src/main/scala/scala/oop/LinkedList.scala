package scala.oop

abstract class LinkedList {
  /*
    head
    tail
    isEmpty
    add(int)
    toString
   */
  def head: Int
  def tail: LinkedList
  def isEmpty: Boolean
  def add(element: Int): LinkedList
  def printElements: String
  // ploymorphic call
  override def toString: String = "[" + printElements + "]"
}

object EmptyList extends LinkedList {
  def head: Int = throw  new NoSuchElementException
  def tail: LinkedList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): LinkedList = new Cons(element, EmptyList)
  def printElements = ""
}

class Cons(h: Int, t: LinkedList) extends LinkedList {
  def head: Int = h
  def tail: LinkedList = t
  def isEmpty: Boolean = false
  def add(element: Int): LinkedList = new Cons(element, this)
  // traverse linked list recursively
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
}

object TestLinkedList extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  println(list.head)
  val newList = list.add(4)
  println(newList.head)
  println(newList.toString)
}


