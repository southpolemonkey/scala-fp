package scala.commands
import scala.filesystem.State

class Cat(name: String) extends Command {

  override def apply(state: State): State = {
    /*
      1. find entry
      2. if directory -> "$ entry is a directory"
      3. print contents
     */

    val wd = state.wd
    val dirEntry = wd.findEntry(name)

    if (dirEntry == null)
      state.setMessage("No such file or directory")
    else if (!dirEntry.isFile)
      state.setMessage(s"$name is a directory")
    else
      state.setMessage(dirEntry.asFile.getContents())
  }
}
