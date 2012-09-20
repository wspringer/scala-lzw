package nl.flotsam.lzw.trie

import nl.flotsam.lzw.io.Appendable

trait Node {
  def encode(b: Byte, out: Appendable[Int]): Node
  def decode(i: Int, out: Appendable[Byte]): Node
  def terminate(out: Appendable[Int]): Unit
}
