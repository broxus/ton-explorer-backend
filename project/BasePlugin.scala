import sbt.Keys._
import sbt.{ AutoPlugin, PluginTrigger, Setting, _ }

object BasePlugin extends AutoPlugin {

  override val trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val Organization: String = "com.broxus.blockchainexplorer"

    lazy val V = new {
      val scala213       = "2.13.3"
      val circe          = "0.13.0"
      val zio            = "1.0.0-RC21-2"
      val doobie         = "0.9.0"
      val zioLogging     = "0.3.2"
      val zioInteropCats = "2.1.4.0-RC17"
      val catsEffect     = "2.1.4"
      val pureconfig     = "0.13.0"
      val http4s         = "0.21.7"
      val log4j          = "2.13.1"
      val tapir          = "0.17.0-M1"
      val flyway         = "6.5.3"
    }

    def zio(module: String): ModuleID        = "dev.zio"                     %% module % V.zio
    def zioLogging(module: String): ModuleID = "dev.zio"                     %% module % V.zioLogging
    def circle(module: String): ModuleID     = "io.circe"                    %% module % V.circe
    def http4s(module: String): ModuleID     = "org.http4s"                  %% module % V.http4s
    def log4j(module: String): ModuleID      = "org.apache.logging.log4j"     % module % V.log4j
    def tapir(module: String): ModuleID      = "com.softwaremill.sttp.tapir" %% module % V.tapir
    def doobie(module: String): ModuleID     = "org.tpolecat"                %% module % V.doobie

    lazy val blockchainexplorerDependencies = Seq(
      zio("zio"),
      zio("zio-streams"),
      zio("zio-macros"),
      zioLogging("zio-logging"),
      zioLogging("zio-logging-slf4j"),
      circle("circe-core"),
      circle("circe-generic"),
      circle("circe-parser"),
      doobie("doobie-core"),
      doobie("doobie-hikari"),
      doobie("doobie-postgres"),
      http4s("http4s-blaze-server"),
      http4s("http4s-circe"),
      http4s("http4s-dsl"),
      log4j("log4j-api"),
      log4j("log4j-core"),
      log4j("log4j-slf4j-impl"),
      tapir("tapir-core"),
      tapir("tapir-json-circe"),
      tapir("tapir-openapi-docs"),
      tapir("tapir-openapi-circe-yaml"),
      tapir("tapir-zio-http4s-server"),
      tapir("tapir-swagger-ui-http4s"),
      "dev.zio"               %% "zio-interop-cats" % V.zioInteropCats,
      "org.typelevel"         %% "cats-effect"      % V.catsEffect,
      "org.flywaydb"           % "flyway-core"      % V.flyway,
      "com.github.pureconfig" %% "pureconfig"       % V.pureconfig
    )

    lazy val MacroSettings: Seq[Setting[_]] = Seq[Setting[_]](
      libraryDependencies ++= Seq(
        scalaOrganization.value % "scala-compiler" % V.scala213 % Provided
      ),
      scalacOptions ++= Seq("-Ymacro-annotations")
    )

    lazy val ScalacSettings: Seq[Setting[_]] = Seq[Setting[_]](
      scalacOptions := Seq(
        "-feature",
        "-deprecation",
        "-explaintypes",
        "-unchecked",
        "-encoding",
        "UTF-8",
        "-language:higherKinds",
        "-language:existentials",
        "-language:implicitConversions",
        "-Xfatal-warnings",
        //"-Xlint:-infer-any,_",
        "-Ypatmat-exhaust-depth",
        "80",
        "-Ywarn-value-discard",
        "-Ywarn-numeric-widen",
        "-Ywarn-extra-implicit"
        //"-Ywarn-unused:_"
      )
    )
  }

}
