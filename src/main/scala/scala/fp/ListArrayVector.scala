package scala.fp

import scala.util.Random

object ListArrayVector extends App {
  // Sequence
  val aSeq = Seq(1,2,3,4)
  println(aSeq)
  println(aSeq.map(_ * 2))

  // Ranges
  println((1 to 10))
  println((1 to 10).map(_ * 2))

  // List
  val aList = Seq(1, 2, 3, 4)
  println(aList.mkString("-"))

  // Arrays - mutable data structure
  val aArray = Array.ofDim(10)
  println(aArray.length)

  // Vectors
  // implemented as a trie

  // a performance benchmark between List and Vector
  // update element
  val maxCapacity = 100000
  val maxRuns = 10000
  def getWriteTime(collection: Seq[Int]) = {
    val r = new Random()
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val listRun = (1 to maxCapacity).toList
  val vectorRun = (1 to maxCapacity).toVector

  println(getWriteTime(listRun))
  println(getWriteTime(vectorRun))
}
