package nl.flotsam.lzw

object Lzw {

  def encode(in: Traversable[Byte]): Traversable[(Int, Int)] =
    new Traversable[(Int, Int)] {
      def foreach[U](f: ((Int, Int)) => U) {
        val root: Node = new RootNode
        val untupled = Function.untupled(f)
        in.foldLeft(root)({ (node, b) => node.encode(b, untupled) }).terminate(untupled)
      }
    }

}
