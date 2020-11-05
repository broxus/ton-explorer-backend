import sbt.Keys._
import sbt._

name := "blockchainexplorer"

organization := Organization

scalaVersion := V.scala213

lazy val `blockchainexplorer` = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    discoveredMainClasses in Compile := Seq("com.broxus.blockchainexplorer.Main"),
    libraryDependencies ++= Seq(
      compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
    ) ++ blockchainexplorerDependencies,
    ScalacSettings,
    MacroSettings
  )
  .dependsOn(
  )
