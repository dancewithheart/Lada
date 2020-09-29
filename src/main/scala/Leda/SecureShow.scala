package Leda

trait SecureShow[T] {
  def show(t: T): String
}

object SecureShow {
  implicit val showString: SecureShow[String] = new SecureShow[String] {
    override def show(s: String): String = s""""$s""""
  }
  implicit val showInt: SecureShow[Int] = _.toString
}
