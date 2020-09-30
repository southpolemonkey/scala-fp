package scala.oop

object Generics extends App {
  print("Generics programming")

  class MyList[A] {

  }

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  // COVARIANCE +A
  // List[Cat] extends List[Animal]

  // INVARIANCE A
  // List[Cat] - List[Cat]

  // CONTRAVARIANCE -A
  // List[Animal] <- List[Cat]

  // it's a very complex topic
  // https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science)



}
