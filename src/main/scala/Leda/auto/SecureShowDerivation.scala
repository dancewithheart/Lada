package Leda.auto

import Leda.SecureShow
import magnolia1.{CaseClass, Magnolia, Param, SealedTrait, Subtype}

object SecureShowDerivation {
  val hideField = List("password")

  type Typeclass[T] = SecureShow[T]

  def join[T](caseClass: CaseClass[SecureShow, T]): SecureShow[T] =
    new SecureShow[T] {
      override def show(t: T): String = {
        val asStr = caseClass.parameters
          .map { p: Param[SecureShow, T] =>
            if (hideField.contains(p.label)) s"${p.label} = ***"
            else s"${p.label} = ${p.typeclass.show(p.dereference(t))}"
          }
        if (asStr.size < 2)
          asStr.mkString(s"${caseClass.typeName.short}(", ", ", ")")
        else asStr.mkString(s"${caseClass.typeName.short}(\n ", ",\n ", ")")
      }
    }

  def split[T](sealedTrait: SealedTrait[SecureShow, T]): SecureShow[T] =
    new SecureShow[T] {
      def show(t: T): String =
        sealedTrait.split(t) { s: Subtype[SecureShow, T] =>
          s.typeclass.show(s.cast(t))
        }
    }

  implicit def gen[T]: SecureShow[T] = macro Magnolia.gen[T]
}
