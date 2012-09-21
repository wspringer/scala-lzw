package nl.flotsam.lzw

trait NodeManager {

  def create(owner: Node, value: Byte, first: Byte): Option[Node]

  def get(value: Int): Option[Node]

}
