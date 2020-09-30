package scala.commands
import scala.filesystem.State

class Exit extends Command {
  override def apply(state: State): State = {
    System.exit(1)
    state.setMessage("Exit")
  }
}
