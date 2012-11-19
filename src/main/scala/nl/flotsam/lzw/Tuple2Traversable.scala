package nl.flotsam.lzw

trait Tuple2Traversable[+A, +B] {

  def foreach[T](fn: (A, B) => T)

  def map[T](fn: (A, B) => T): Traversable[T] = new Traversable[T] {
    def foreach[U](f: (T) => U) {
      def composed(a: A, b: B) = f(fn(a, b))
      Tuple2Traversable.this.foreach(composed)
    }
  }

  def flatMap[T](fn: (A, B) => Traversable[T]): Traversable[T] = new Traversable[T] {
    def foreach[U](f: (T) => U) {
      def composed(a: A, b: B) = fn(a, b).foreach(f)
      Tuple2Traversable.this.foreach(composed)
    }
  }

  def filter(included: (A, B) => Boolean): Tuple2Traversable[A, B] = new Tuple2Traversable[A, B] {
    def foreach[T](fn: (A, B) => T) {
      def composed(a: A, b: B) = if (included(a, b)) fn(a, b)
      Tuple2Traversable.this.foreach(composed)
    }
  }

  def foldLeft[T](z: T)(fn: (T, A, B) => T): T = {
    var current = z
    def op(a: A, b: B) {
      current = fn(current, a, b)
    }
    foreach(op)
    current
  }

  def asTraversable = new Traversable[(A, B)] {
    def foreach[U](f: ((A, B)) => U) {
      def tupled(a: A, b: B) = f((a, b))
      Tuple2Traversable.this.foreach(tupled)
    }
  }

}