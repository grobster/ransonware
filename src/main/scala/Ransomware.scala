package fake

import java.nio.file._

object FakeRansomware {
	def removeFileExtension(fileName: String): Option[String] = fileName match {
		case null => None
		case _ => {
			val position: Int = fileName.lastIndexOf(".")
			if (position == -1) return None
			
			Some(fileName.substring(0, position))
		}
	}
	
	def encrypt(fileName: String)(implicit fileEnding: String): String = removeFileExtension(fileName).get + fileEnding
	
	def main(args: Array[String]): Unit = {
		val test1 = "finances.txt"
		implicit val zebraEnding: String = ".zebra"
		val newFile = encrypt(test1)
		
		println("original file name: " + test1 + " New file name: " + newFile)
	}
}