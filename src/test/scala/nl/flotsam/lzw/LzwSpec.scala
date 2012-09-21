package nl.flotsam.lzw

import org.specs2.mutable.Specification

class LzwSpec extends Specification {

  "Lzw" should {

    "be able to encode a sequence of bytes appropriately" in {
      println(Lzw.encode("sir eastman easily teases sea sick seals".getBytes("utf-8")).mkString(", "))
      ok
    }

  }

}
