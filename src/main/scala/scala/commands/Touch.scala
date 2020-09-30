package scala.commands
import scala.filesystem.State
import scala.files.{DirEntry, Directory, File}

class Touch(name: String) extends CreateEntry(name) {

  override def CreateSpecificEntry(state: State, entryName: String): DirEntry = {
    File.empty(state.wd.path, entryName)
  }
}
