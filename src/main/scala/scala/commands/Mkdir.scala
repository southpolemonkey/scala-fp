package scala.commands
import java.util.jar.Attributes.Name

import scala.filesystem.State
import scala.files.{DirEntry, Directory}

class Mkdir(name: String) extends CreateEntry(name) {

  override def CreateSpecificEntry(state: State, entryName: String): DirEntry = {
    Directory.empty(state.wd.path, entryName)
  }
}


