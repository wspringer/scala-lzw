package nl.flotsam.lzwold

import org.specs2.mutable.Specification
import collection.mutable.ArrayBuffer
import Lzw._

class LzwSpec extends Specification {

  "Lzw" should {

    "be able to encode a String" in {
      Lzw.encode("blablablablablablabla") must beEqualTo(ArrayBuffer(98, 108, 97, 256, 258, 257, 259, 262, 261, 257))
    }

    "be able to decode a sequence of bytes into a String" in {
      println(Lzw.encode("ababababa"))
      println(Lzw.decode(ArrayBuffer(97, 98, 256, 258, 257)))
      println(Lzw.decode(ArrayBuffer(98, 108, 97, 256, 258, 257, 259, 262, 261, 257)))
    }

  }

}
