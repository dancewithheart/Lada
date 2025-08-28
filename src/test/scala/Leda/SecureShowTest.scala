package Leda

import zio.test.Assertion._
import zio.test._

object SecureShowTest extends ZIOSpecDefault {

  def spec = suite("show")(
    test("convert case class with data to string") {
      // given
      import Leda.auto.SecureShowDerivation._

      case class Foo(user: String, age: Long, initial: Char, salary: Double)

      val foo = Foo(user = "John", age = 42, salary = 50.0, initial = 'J')

      // when
      val fooStr = implicitly[SecureShow[Foo]].show(foo)

      // then
      val expected =
        """|Foo(
           | user = "John",
           | age = 42L,
           | initial = 'J',
           | salary = 50.0)""".stripMargin.trim

      assert(fooStr)(equalTo(expected))
    },
    test("convert case class with data masking password field") {
      // given
      import Leda.auto.SecureShowDerivation._

      case class S3Config(user: String, password: String, bucket: String)
      case class Config(timeout: Int, s3: S3Config)

      val conf =
        Config(timeout = 90, S3Config("admin", "password", "s3://acme"))

      // when
      val confStr = implicitly[SecureShow[Config]].show(conf)

      // then
      val expected =
        """
          |Config(
          | timeout = 90,
          | s3 = S3Config(
          | user = "admin",
          | password = ***,
          | bucket = "s3://acme"))
          |""".stripMargin.trim

      assert(confStr)(equalTo(expected))
    }
  )
}
