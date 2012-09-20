package nl.flotsam.lzw.io

trait Appendable[T] {
  def append(value: T)
}
