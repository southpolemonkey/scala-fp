package scala.commands
import scala.filesystem.State

class Pwd extends Command {

  override def apply(state: State): State = {
    val currentDir = state.wd.path
    state.setMessage(currentDir)
  }

}
