package scala.files

abstract class DirEntry(val parentPath: String, val name: String) {

  def asDirectory: Directory
  def asFile: File

  def path(): String = {
    val separator =
      if ("/".equals(parentPath)) ""
      else Directory.SEPARATOR

    parentPath + separator + name
  }

  def getType:String

  def isDirectory: Boolean
  def isFile: Boolean

}
