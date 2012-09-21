package nl.flotsam.lzwold.trie

import nl.flotsam.lzwold.io.Appendable

class ValueNode(value: Int, rootNode: RootNode, val path: Array[Byte]) extends Node {

  private val nodes = new Array[Node](255)

  def encode(b: Byte, out: Appendable[Int]): Node = Option(nodes(0xff & b)).getOrElse {
    out.append(value)
    rootNode.createNode(path :+ b) match {
      case Some(node) =>
        nodes(0xff & b) = node
        rootNode.encode(b, out)
      case None =>
        rootNode.encode(b, out)
    }
  }

  def decode(i: Int, out: Appendable[Byte]): Node = {
    val node = rootNode.nodeByValue(i).get
    val firstByte = node.path.head
    for (b <- node.path) out.append(b)
    if (nodes(0xff & firstByte) == null) {
      val next = rootNode.createNode(path :+ firstByte).get
      nodes(0xff & firstByte) = next
    }
    node
  }

  def terminate(out: Appendable[Int]) {
    out.append(value)
  }

  override def toString = "ValueNode(" +
    value + (if (value < 256) "'" + value.toChar + "'" else "") +
    ")"

}