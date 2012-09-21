package nl.flotsam.lzw

trait Node {

  /**
   * For every byte getting passed in, check we already have some results and call the function passed in, and then
   * return the next Node.
   *
   * @param b The byte read.
   * @param fn The function to be applied to data decoded.
   * @tparam T The result of that function.
   * @return The new Node.
   */
//   def decode[T](b: Byte, fn: (Int) => T): Node

  /**
   * For every byte getting passed in, check we already have some results and call the function passed in, and then
   * return the next Node.
   *
   * @param b The byte read.
   * @param fn The function to be applied to data encoded.
   * @tparam T The result of that function.
   * @return The new Node.
   */
  def encode[T](b: Byte, fn: (Int, Int) => T): Node

  def apply[T](fn: (Int, Int) => T)

  def root: Node

  def bitsRequired: Int

  def terminate[T](fn: (Int, Int) => T): Unit

}
