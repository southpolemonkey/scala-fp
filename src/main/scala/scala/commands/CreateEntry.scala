package scala.commands
import scala.files.{DirEntry, Directory}
import scala.filesystem.State

abstract class CreateEntry(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry " + name + " has existed!")
    }else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + "must not contain separators!")
    }else if (checkIllegal(name)) {
      state.setMessage(name + "has illegal characters!")
    }else {
      doCreateEntry(state, name)
    }
  }

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doCreateEntry(state: State, name: String): State = {

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      /*
        Directory is immutable, after a new DirEntry are created, a new Directory is copied and returned
        old directory
        /a/b
          /c

        /a/b
            /e (new dir)
          /c

        (new directory)
        /a (new)
          /b (new)
            /e
          /c (new)
       */
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    // 1. get all directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    val newDir = Directory.empty(wd.path, name)
    val newEntry: DirEntry = CreateSpecificEntry(state, name)

    // 3. update the whole directory structure starting from the root
    val newRoot: Directory = updateStructure(state.root, allDirsInPath, newEntry)

    // 4. find new working directory INSTANCE given wd's full path, in the NEW directory structure
    val newWd: Directory = newRoot.findDescendant(allDirsInPath)

    // 5. return the new State
    // State(state.root, state.wd)
    State(newRoot, newWd)

  }

  def CreateSpecificEntry(state: State, entryName: String): DirEntry
}
