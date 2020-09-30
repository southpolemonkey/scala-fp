package scala.commands
import scala.files.DirEntry
import scala.filesystem.State

class Ls extends Command {
  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = prettyPrint(contents)
    state.setMessage(niceOutput)
  }

  def prettyPrint(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name + "[" + entry.getType + "]" + "\n" + prettyPrint(contents.tail)
    }

  }


}
