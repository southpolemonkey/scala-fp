package advanced

object pattern extends App {

  /*
    An extractor object is an object with an unapply method.
    - apply: take arguments and construct objects
    - unapply: take object and return the arguments
    - unapplyseq: extractor for variable pattern
   */

  class Person(val name: String, val age: Int)

  object Person {

    def apply(name: String) = s"$name"

    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else Some((person.name, person.age))
  }

  val alex = new Person("alex", 28)
  val greeting = alex match {
    case Person(n, a) => s"You're $n and $a yrs old"
    case _            => "Not identified"
  }

  println(greeting)

  // unapplyseq
  // decompose sequence
  abstract class MyList[+T] {
    def head: T         = ???
    def tail: MyList[T] = ???
  }

  case object Empty                                                       extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1, 2"
    case _                => "something else"
  }

  println(decomposed)

  // custom return types for unapply
  // isEmpty: Boolean, get: something.
  // Not clear to me --- skip

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] =
      new Wrapper[String] {
        def isEmpty = false
        def get     = person.name
      }
  }

  println(alex match {
    case PersonWrapper(n) => s"This person's name is $n"
    case _                => "An alien"
  })

}
