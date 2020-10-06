val prependedlist = List(3, 4).map(x => x.toString) ++ List("5", "6")

val a = List(1,2,3,4)

def unapply_seq(list: List[Int]): Option[Seq[Int]] =
  if (list == List[Nothing]()) Some(Seq.empty)
  else unapply_seq(list.tail).map(list.head * 2 +: _)

val b = unapply_seq(a)
println(b)

val m = Map("a" -> 1, "b" -> 2)
println(m.flatMap(x => x.toString()))

def g(v:Int) = List(v-1, v, v+1)

a.map(x => g(x))

m.flatMap(x => List(x._2))

m.toList.foreach(x => println(x._1 + x._2))

m + ("c" -> 3)
m + ("a" -> 2)

m - "c"
m - "a"
m - "a"

// mutable Map
var mm = scala.collection.mutable.Map("a" -> 1, "b" -> 2)
mm += ("a" -> 22)

mm = scala.collection.mutable.Map("CO" -> 2032)
mm("a") = 9999
mm - "c"
println(mm)

val se = Seq(1,2,3,4)
se.map(x => List(x, x*2)).flatten
se.flatMap(x => List(x, x*2))

val s = Set(1,2,3,4)
println(s(5))

lazy val x = { println("calculating value of x");13 }
val y = { println("calculating value of x");13 }
(y + y)
(x + x)

(1 to 5).reduceLeft(_ + _)

(1 to 5).map { x =>
  val y = x * 2
  println(y)
  y
}

(1 to 5) map (_ * 2)

(1 to 5).map(x => x*2)

// assign the given tuple to each listed variable
var x, y, z = (1, 2, 3)

val xs = 1 to 10 by 2 // [1,3,5,7,9]
val ys = 1 to 10 by 3 // [1,4,7,10]


//val aa = for (x <- xs; y <- ys) println(x * y)
val bb = xs.flatMap(x => ys.map(y => x * y))
for (x <- bb) println(x)

// stream
def fibFrom(a: Int, b: Int): Stream[Int] = a #:: fibFrom(b, a + b)
val fibs = fibFrom(1, 1).take(10)
fibs.toList

collection.immutable.IndexedSeq(1, 2, 3)
val aList = collection.immutable.List(1, 2, 3)
aList :+ 4
5 +: aList
List(5,6,7,8) ++ aList
aList.head
aList.tail
// stack-push
999 :: aList
// stack-push
aList.last

val aQueue = scala.collection.immutable.Queue[Int]()
val aNewQueue = aQueue.enqueue(List(1,2,3))
aNewQueue.enqueue(4)
val (element, has123) = aNewQueue.dequeue
val (element, has12) = has123.dequeue



