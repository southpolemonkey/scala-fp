package scala.oop

object Trait extends App {

  // trait vs abc(abstract class)
  // 1. traits does not have constructor parameters
  // 2. scala supports multiple trait inheritance
  // 3. traits = behaviour, abc = type

  trait Carnivore
  trait ColdBlooded

  class Croc extends Carnivore with ColdBlooded
}
