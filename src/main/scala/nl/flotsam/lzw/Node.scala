package nl.flotsam.lzw

trait Node {

  def decode[T](i: Int, fn: (Byte) => T): Node

  def encode[T](b: Byte, fn: (Int, Int) => T): Node

  def apply[T](fn: (Byte) => T)

  def root: Node

  def bitsRequired: Int

  def terminate[T](fn: (Int, Int) => T)

  def first: Byte

}
