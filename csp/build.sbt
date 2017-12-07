name := "csp"

version := "2017.0.4-SNAPSHOT"

scalaVersion := "2.12.4"

enablePlugins(ProtobufPlugin)

javaSource in ProtobufConfig := ((sourceDirectory in Compile).value / "generated")