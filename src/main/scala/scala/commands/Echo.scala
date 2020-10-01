package scala.commands

import scala.files.{DirEntry, Directory, File}
import scala.filesystem.State

class Echo(args: List[String]) extends Command {
  override def apply(state: State): State = {

    // 1. if no args, state
    // 2. if just one arg, print
    // 3. if list of args
    // if > echo to a file
    // if >> append to a file
    // else echo everything to console

    if (args.length == 0) state
    else if (args.length == 1) {
      state.setMessage(args(0))
    } else {
      val operator = args(args.length - 2)
      val filename = args(args.length - 1)
      val contents = createContent(args, args.length - 2)

      if (operator.equals(">")) {
        doEcho(state, contents, filename, false)
      } else if (operator.equals(">>")) {
        doEcho(state, contents, filename, true)
      } else {
        state.setMessage(createContent(args, args.length))
      }
    }
  }

  def createContent(args: List[String], topIndex: Int): String = args.take(topIndex).mkString(" ") + "\n"

  def doEcho(state: State, contents: String, filename: String, append: Boolean) = {

    /*
      operator >
        if file exists: replace
        else new file
      operator >>
        if file exists: append
        else new file
     */

    def echoHelper(state: State, contents: String, append: Boolean, filename:String): Directory ={
      val wd = state.wd
      if (!append) {
        val file = new File(wd.parentPath, filename, contents)
        if (wd.hasEntry(filename)) {
          wd.replaceEntry(filename, file)
        } else {
          wd.addEntry(file)
        }
      } else {
        if (!wd.hasEntry(filename)) {
          val file = new File(wd.parentPath, filename, contents)
          wd.addEntry(file)
        } else {
          val updatedFile:File = wd.findEntry(filename).asFile.updateContents(contents)
          wd.replaceEntry(filename, updatedFile)
        }
      }
    }

    val newWd:Directory = echoHelper(state, contents, append, filename)

    State(state.root, newWd, state.output)
  }
}
