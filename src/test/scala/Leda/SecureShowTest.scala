package Leda

import zio.test.Assertion._
import zio.test._
import zio.test.junit.JUnitRunnableSpec

object SecureShowTest extends JUnitRunnableSpec {

  def spec = suite("show")(
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
