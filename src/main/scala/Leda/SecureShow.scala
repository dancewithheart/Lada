package Leda

trait SecureShow[T] {
  def show(t: T): String
}

object SecureShow {
  implicit val showString: SecureShow[String] = new SecureShow[String] {
    override def show(s: String): String = s""""$s""""
  }
  implicit val showInt: SecureShow[Int] = _.toString
  implicit val showLong: SecureShow[Long] = n => s"""${n}L"""
  implicit val showDouble: SecureShow[Double] = _.toString
  implicit val showFloat: SecureShow[Float] = _.toString
  implicit val showChar: SecureShow[Char] = c => s"""'$c'"""
}
