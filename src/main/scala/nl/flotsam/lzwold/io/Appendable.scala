package nl.flotsam.lzwold.io

trait Appendable[T] {
  def append(value: T)
}
