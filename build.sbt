import sbt._
import Dependencies._
import BuildConstants._


// ------------------------------------------------------
lazy val tutorial = (project in file("tutorial/")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "tutorial",
    libraryDependencies += scalaTest,
    fork := true
  )

lazy val tree = (project in file("tree/")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "tree",
    libraryDependencies += scalaTest,
    fork := true
  )

lazy val functional = (project in file("functional/")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "functional",
    libraryDependencies += scalaTest,
    fork := true
  )

lazy val calculator = (project in file("calculator/")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "calculator",
    libraryDependencies += scalaTest,
    fork := true
  )

// ------------------------------------------------------
// main project
lazy val assignments = (project in file(".")).
  settings(
    organization := org,
    scalaVersion := scalaVer,
    version := buildVer,
    name := "assignments",
    libraryDependencies += scalaTest
  ).aggregate(tutorial, functional, tree, calculator)