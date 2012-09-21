package nl.flotsam.lzw

class ValueNode(value: Int, owner: Node, factory: NodeFactory) extends Node {

  private val children = new Array[Node](255)

  def decode[T](b: Byte, fn: (Int) => T) = null

  def encode[T](b: Byte, fn: (Int, Int) => T) = {
    fn(value, bitsRequired)
    val child = children(0xff & b)
    if (child == null) {
      factory.createNode(this) match {
        case Some(node) =>
          children(0xff & b) = node
        case _ =>
      }
      root.encode(b, fn)
    } else child
  }

  def apply[T](fn: (Int, Int) => T) {
    owner.apply(fn)
    fn(value, bitsRequired)
  }

  def root = owner.root

  def bitsRequired = owner.bitsRequired

  def terminate[T](fn: (Int, Int) => T) {
    fn(value, bitsRequired)
  }

}
