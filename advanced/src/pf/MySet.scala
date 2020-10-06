package pf

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean){

  def apply(elem: A): Boolean = contains(elem)

  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit

  def -(elem: A): MySet[A]
  def intersection(anotherSet: MySet[A]): MySet[A]
  def diff(anotherSet: MySet[A]): MySet[A]

  // EX3. unary negation
  def unary_! : MySet[A]
}

// concrete implementation
class EmptySet[A] extends MySet[A] {

  def contains(elem: A): Boolean = false
  def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)
  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  def map[B](f: A => B): MySet[B] = new EmptySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]
  def filter(predicate: A => Boolean): MySet[A] = this
  def foreach(f: A => Unit): Unit = ()
  def -(elem: A): MySet[A] = this
  def intersection(anotherSet: MySet[A]): MySet[A] = this
  def diff(anotherSet: MySet[A]): MySet[A] = this
  def unary_! : MySet[A] = this
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  def contains(elem: A): Boolean =
    elem == head || tail.contains(elem)

  def +(elem: A): MySet[A] =
    if (this contains elem) this
    else new NonEmptySet[A](elem, this)

  def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head
  def -(elem: A): MySet[A] = this.filter(x => x != elem)
  def unary_! : MySet[A] = ???

  def intersection(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
  def diff(anotherSet: MySet[A]): MySet[A] = this.filter(x => !anotherSet.contains(x))

  def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  def filter(predicate: A => Boolean): MySet[A] = {
    val filteredTail = tail.filter(predicate)
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)


  }
}

  object MySet {
    def apply[A](value: A*): MySet[A] = {
      @tailrec
      def builtSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] =
        if (valSeq.isEmpty) acc
        else builtSet(valSeq.tail, acc + valSeq.head)

      builtSet(value.toSeq, new EmptySet[A])
    }
}

object settest extends App {
  val s = MySet(1,2,3,4)
  val p = MySet(2,4,5)
  val t1 = ((s + 5) ++ MySet(-4,5,6)).map(x => x.abs).flatMap(x => MySet(x, x * 10)).filter(_ % 2 !=0)
  val t2 = s - 3 intersection p
  val t3 = s diff p
  t1.foreach(print)
  println()
  t2.foreach(print)
  println()
  t3.foreach(print)

}
