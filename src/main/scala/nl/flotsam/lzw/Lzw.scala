package nl.flotsam.lzw

import collection.generic.Growable
import io.{Appendable, GrowingAppendable}
import trie.{RootNode, Node}

object Lzw {

  implicit def growableToAppendable[T](growable: Growable[T]) = new GrowingAppendable(growable)

  implicit def stringToByteIterable(str: String) = str.getBytes.toIterable

  def encode[F <% Iterable[Byte], T <% Appendable[Int]](in: F, out: T) =
    in.foldLeft(new RootNode().asInstanceOf[Node])({ (node, b) => node.encode(b, out) }).terminate(out)

  def decode[F <% Iterable[Int], T <% Appendable[Byte]](in: F, out: T) =
    in.foldLeft(new RootNode().asInstanceOf[Node])({ (node, i) => node.decode(i, out) })

  def encode[F <% Iterable[Byte]](in: F): Seq[Int] = {
    val buffer = new scala.collection.mutable.ArrayBuffer[Int]
    encode(in, buffer)
    buffer
  }

  def decode[F <% Iterable[Int]](in: F): Seq[Byte] = {
    val buffer = new scala.collection.mutable.ArrayBuffer[Byte]
    decode(in, buffer)
    buffer
  }

}
