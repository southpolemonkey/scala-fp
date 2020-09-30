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

    if (wd.hasEntry(name)) {
      if (wd.findEntry(name).isDirectory) state.setMessage(s"$name is a directory")
      else {
        val content:String = wd.findEntry(name).asFile.getContents()
        state.setMessage(content)
      }
    } else state.setMessage("No such file")
  }
}
