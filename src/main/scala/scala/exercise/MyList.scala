package scala.exercise

/*
  A generic linked list class
 */

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B]{
  def transform(elem: A): B
}


abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // ploymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatmap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
  
}


object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw  new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, EmptyList)
  def printElements = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList
  def flatmap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  // traverse linked list recursively
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  def map[B](transformer: MyTransformer[A,B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def flatmap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatmap(transformer)
  }

  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons[A](h, t.filter(predicate))
    else t.filter(predicate)
  }

}


object TestLinkedList extends App {

  val list: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  val anotherlist: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, EmptyList)))

  println(list.toString)

  /*
  val triplelist: MyList[Int] = list.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 3
  })
   */

  val triplelist: MyList[Int] = list.map(elem => elem * 2)

  println(triplelist.toString)

  println(triplelist.filter(elem => elem > 3))

  println((list.++(anotherlist)).toString)

  println(triplelist.flatmap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons[Int](elem, new Cons[Int](elem + 1, EmptyList))
  }).toString)

//  println(triplelist.flatmap(elem => new Cons(elem, new Cons(elem + 1, EmptyList))).toString)
}