package nl.flotsam.lzw

trait NodeFactory {

  def createNode(owner: Node): Option[Node]

}
