name := "Lada"

version := "0.0.1"

scalaVersion := "2.13.16"

lazy val root = project in file(".")

val zioVersion = "2.1.20"
libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
  "com.softwaremill.magnolia1_2" %% "magnolia" % "1.1.10",
  "dev.zio"         %% "zio-test"       % zioVersion % Test,
  "dev.zio"         %% "zio-test-sbt"   % zioVersion % Test
)
