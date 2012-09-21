package nl.flotsam.lzw

class RootNode(limit: Int = 512) extends Node with NodeFactory {

  private var index = 255

  private val initial = Array.tabulate[ValueNode](256)(b => new ValueNode(b, this, this))

  private val createdNodes = new Array[ValueNode](limit - 256)

  def encode[T](b: Byte, fn: (Int, Int) => T) = initial(0xff & b)

  def apply[T](fn: (Int, Int) => T) {}

  def terminate[T](fn: (Int, Int) => T) {}

  val root = this

  def createNode(owner: Node) = if (index <= limit) {
    val node = new ValueNode(index, owner, this)
    index += 1
    createdNodes(index - 256) = node
    Some(node)
  } else {
    // No reset
    None
  }

  def bitsRequired = 32 - Integer.numberOfLeadingZeros(index)

}
