package scala.oop

object OBasic extends App {
  val person = new Person("Alex", 28)
  println(s"${person.name} is ${person.age} years old")
  person.greet()

  val writer = new Writer("Chenxuan", "Rong", 28)
  println(writer.fullname())

  val novel = new Novel("scala book", 2020, writer)

  val anotherwriter = new Writer("John", "Tulic", 30)
  println(novel.isWritternBy(anotherwriter))
  println(novel.authorAge())
  val newNovel = novel.copy(2018)
  println(newNovel)

  // counter
  val counter = new Counter()
  counter.inc.inc.inc.dec.dec.print
}

class Person(val name: String, val age: Int=0) {
  val x = 2

  def greet(name: String): Unit = println("hello" + this.name)

  // overloading
  def greet(): Unit = println("Hi, I am " + this.name)

  // multiple constructors
  //  def this(name: String) = this(name)
  def this() = this("John")
}
  /*
    Novel and writer
  */
class Writer(fname: String, lName: String, val year: Int) {

  def fullname():String = fname + " " + lName
}

class Novel(name: String, year_of_release: Int, author: Writer){

  def authorAge():Int = year_of_release - author.year

  def isWritternBy(author: Writer):Boolean = author == this.author

  def copy(revised_year_of_release: Int): Novel = new Novel(name, revised_year_of_release, author)

}

  /*
    Counter class

   */
class Counter(val n: Int=0) {
    def inc = new Counter(n + 1)
    def dec = new Counter(n - 1)

    def inc(value: Int) = new Counter(n - value)
    def dec(value: Int) = new Counter(n - value)

    def print():Unit = println(n)

}



