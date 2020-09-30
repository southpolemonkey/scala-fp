package scala.commands

import scala.filesystem.State

trait Command {

  def apply(state: State): State

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
    val args = cmds.tail.toList

    if (cmds.isEmpty) emptyCommand
//    else if (cmds.length == 1) println("incomplete command")
    else if (cmd.equals(MKDIR)) {
      if (cmds.length < 2) incompletedCommand(cmd)
      else new Mkdir(cmds(1))
    } else if (cmd.equals(LS)) {
      new Ls
    } else if (cmd.equals(PWD)) {
      new Pwd
    } else if (cmd.equals(TOUCH)) {
      if (cmds.length < 2) incompletedCommand(cmd)
      else new Touch(cmds(1))
    } else if (cmd.equals(CD)) {
      if (cmds.length < 2) incompletedCommand(cmd)
      else new Cd(cmds(1))
    } else if (cmd.equals(EXIT)) {
      new Exit
    } else if (cmd.equals(RM)) {
      new Rm(cmds(1))
    } else if (cmd.equals(ECHO)) {
      if (cmds.length < 2) new Echo(List())
      else new Echo(args)
    } else if (cmd.equals(CAT)) {
      if (cmds.length < 2) incompletedCommand(cmd)
      else new Cat(cmds(1))
    }
    else new UnknownCommand

//    cmd.toUpperCase match {
//      case "LS" => new Ls
//      case "TOUCH" => new Touch
//      case "MKDIR" => new Mkdir
//      case _ => new UnknownCommand
//    }
  }
}
