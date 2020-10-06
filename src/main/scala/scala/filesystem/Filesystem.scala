package scala.filesystem

import java.util.Scanner

import scala.files.Directory
import scala.commands.Command

object Filesystem extends App {

  val root = Directory.ROOT

  // use functional way to handle input as stream
//  io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
//    currentState.show
//    Command.from(newLine).apply(currentState)
//  })

  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }

}
