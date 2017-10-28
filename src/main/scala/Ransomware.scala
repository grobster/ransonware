package fake

import java.nio.file._
import java.io.File

object FakeRansomware {
	/** This function converts from an exception based API to an Option based API.
	  */
	def Try[A](a: => A): Option[A] = try Some(a) catch { case e: Exception => None }
	
	/**
	  * This function take a file name and remove its ending, and
	  * returns an Option[String] if String is not equal to
	  * null or if fileName contains a period in it.
	  */
	def removeFileExtension(fileName: String): Option[String] = fileName match {
		case null => None
		case _ => {
			val position: Int = fileName.lastIndexOf(".")
			if (position == -1) return None
			
			Some(fileName.substring(0, position))
		}
	}
	
	/**
	  * This function simulates ransomware encryption on a file.
	  * It simply changes the file extension and returns the new fileName.
	  */
	def encrypt(fileName: String)(implicit fileEnding: String): String = removeFileExtension(fileName).get + fileEnding
	
	def folderToList(folder: Path): List[Path] = if(Files.exists(folder) && Files.isDirectory(folder)) folder.toFile.listFiles.toList.map(f => f.toPath) else List[Path]()
	
	/** This function returns an Option[Int] of the last postion
	  * of v: Char in a string.
	  * If no instance of v: Char is found in String, then None is returned.
	  */
	def lastIndex(s: String, v: Char): Option[Int] = s match {
		case null => None
		case d if(!s.contains(v)) => None
		case _ => Some(s.lastIndexOf(v))
	}
	
	/** This function converts a java.nio.file.Path to a List of all files and sub-folders.
	  */
	def scan(dir: Path): List[Path] = {
		def _scan(dir: List[Path], acc: List[Path]): List[Path] = dir match {
			case Nil => acc
			case h :: t if(Files.isDirectory(h)) => _scan(folderToList(h) ::: t, acc)
			case h :: t => _scan(t, h :: acc)
		}
		_scan(List(dir), Nil)
	}
}