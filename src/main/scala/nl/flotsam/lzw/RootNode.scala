package nl.flotsam.lzw

class RootNode(limit: Int = 512) extends Node with NodeManager {

  private var index = 255

  private val initial = Array.tabulate[ValueNode](256)(b => new ValueNode(b, this, b.toByte, b.toByte, this))

  private val createdNodes = new Array[ValueNode](limit - 256)

  def decode[T](i: Int, fn: (Byte) => T) = {
    val node = initial(i)
    node.apply(fn)
    node
  }

  def encode[T](b: Byte, fn: (Int, Int) => T) = initial(0xff & b)

  def apply[T](fn: (Byte) => T) { }

  def terminate[T](fn: (Int, Int) => T) {}

  def first = 0

  val root = this

  def create(owner: Node, value: Byte, first: Byte) = if (index <= limit) {
    index += 1
    val node = new ValueNode(index, owner, value, first, this)
    createdNodes(index - 256) = node
    Some(node)
  } else {
    // No reset
    None
  }

  def get(value: Int): Option[Node] =
    if (value < 256) Some(initial(value))
    else if (value > index) None
    else Some(createdNodes(value - 256))

  def bitsRequired = 32 - Integer.numberOfLeadingZeros(index)

}
