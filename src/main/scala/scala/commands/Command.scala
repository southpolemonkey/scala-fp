package scala.commands

import scala.filesystem.State

trait Command extends (State => State){
}

object Command {

  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  val TOUCH = "touch"
  val CD = "cd"
  val EXIT = "exit"
  val RM = "rm"
  val ECHO = "echo"
  val CAT = "cat"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompletedCommand(name: String) = new Command {
    override def apply(state: State): State = state.setMessage(s"${name}: miss arguments")
  }

  def from(input: String): Command = {
    val cmds: Array[String] = input.split(" ").map(_.trim)
    val cmd = cmds(0)

    if (cmds.isEmpty) emptyCommand
    else cmd match {
      case MKDIR =>
        if (cmds.length < 2) incompletedCommand(cmd)
        else new Mkdir(cmds(1))
      case LS =>
        new Ls
      case PWD =>
        new Pwd
      case TOUCH =>
        if (cmds.length < 2) incompletedCommand(cmd)
        else new Touch(cmds(1))
      case CD =>
        if (cmds.length < 2) incompletedCommand(cmd)
        else new Cd(cmds(1))
      case RM =>
        new Rm(cmds(1))
      case ECHO =>
        if (cmds.length < 2) new Echo(List())
        else new Echo(cmds.tail.toList)
      case CAT =>
        if (cmds.length < 2) incompletedCommand(cmd)
        else new Cat(cmds(1))
      case EXIT =>
        new Exit
      case _ =>
        new UnknownCommand
    }
  }
}
