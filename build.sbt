import Keys._

seq(slickSettings: _*)

seq(oldLwjglSettings: _*)

name := "KrossaKlossar"

organization := "se.ramn"

version := "0.1.0-SNAPSHOT"

unmanagedBase <<= baseDirectory { base => base / "lib" }

//fork := true

javaOptions += "-Djava.library.path=./lib/native/macosx"

scalaVersion := "2.10.1"

scalacOptions := Seq(
  "-unchecked",
  "-feature",
  "-deprecation",
  "-encoding", "utf8"
  //"-Xlint"
  //"-explaintypes",
  //"-optimise"
  //"-Ydependent-method-types"
  //"-Xcheck-null"
)
