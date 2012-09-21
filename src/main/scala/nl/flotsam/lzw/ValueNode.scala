package nl.flotsam.lzw

class ValueNode(index: Int, owner: Node, value: Byte, val first: Byte, nodeManager: NodeManager) extends Node {

  private val children = new Array[Node](255)

  def decode[T](i: Int, fn: (Byte) => T) = {
    val node = nodeManager.get(i).get
    node.apply(fn)
    val child = children(0xff & node.first)
    if (child == null) {
      nodeManager.create(this, node.first, this.first) match {
        case Some(nxt) => children(0xff & node.first) = nxt
        case _ =>
      }
    }
    node
  }

  def encode[T](b: Byte, fn: (Int, Int) => T) = {
    val child = children(0xff & b)
    if (child == null) {
      fn(index, bitsRequired)
      nodeManager.create(this, b, first) match {
        case Some(node) => children(0xff & b) = node
        case _ =>
      }
      root.encode(b, fn)
    } else child
  }

  def apply[T](fn: (Byte) => T) {
    owner.apply(fn)
    fn(value)
  }

  def root = owner.root

  def bitsRequired = owner.bitsRequired

  def terminate[T](fn: (Int, Int) => T) {
    fn(index, bitsRequired)
  }

}
