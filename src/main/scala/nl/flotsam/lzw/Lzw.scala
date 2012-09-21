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

  def decode(factory: (() => Int) => Traversable[Int]): Traversable[Byte] = {
    new Traversable[Byte] {
      def foreach[U](f: (Byte) => U) {
        val root: Node = new RootNode
        val in = factory({ () => root.bitsRequired })
        in.foldLeft(root)({ (node, i) => node.decode(i, f)})
      }
    }
  }

}
