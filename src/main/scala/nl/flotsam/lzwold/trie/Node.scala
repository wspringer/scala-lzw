package nl.flotsam.lzwold.trie

import nl.flotsam.lzwold.io.Appendable
import java.io.Writer

trait Node {
  def encode(b: Byte, out: Appendable[Int]): Node
  def decode(i: Int, out: Appendable[Byte]): Node
  def terminate(out: Appendable[Int]): Unit
}
