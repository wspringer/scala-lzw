package nl.flotsam.lzwold.trie

import nl.flotsam.lzwold.io.Appendable

class RootNode(limit: Int = 512) extends Node {

  private var index = 256

  private val initial = Array.tabulate[ValueNode](256)(b => new ValueNode(b, this, Array[Byte](b.asInstanceOf[Byte])))

  private val createdNodes = new Array[ValueNode](limit - 256)

  def encode(b: Byte, out: Appendable[Int]): Node = initial(0xff & b)

  def decode(i: Int, out: Appendable[Byte]): Node = {
    val node = nodeByValue(i).get
    for (b <- node.path) out.append(b)
    node
  }

  def createNode(path: Array[Byte]): Option[Node] = {
    if (index <= limit) {
      val node = new ValueNode(index, this, path)
      createdNodes(index - 256) = node
      index += 1
      Some(node)
    } else {
      // In this particular implementation, there is no reset (yet)
      None
    }
  }

  def nodeByValue(value: Int): Option[ValueNode] =
    // This method should use asserts rather than options
    if (value < 256) Some(initial(value))
    else if (value > index) None
    else Some(createdNodes(value - 256))

  def terminate(out: Appendable[Int]) { }

  override def toString = "RootNode"
}
