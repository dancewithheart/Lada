name := "Lada"

version := "0.0.1"

scalaVersion := "2.13.3"

lazy val root = (project in file("."))

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,

  // magnolia
  "com.propensive" %% "magnolia" % "0.16.0",
  "org.scalatest" %% "scalatest" % "3.2.0" % Test
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8"
)
