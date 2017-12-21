package at.fhj.swengb.apps.calculator

import java.nio.file.{Files, Path, Paths}
import java.util

import org.scalatest.WordSpecLike
import scala.collection.JavaConverters._


class TimesheetSpec extends WordSpecLike {


  val p: Path = Paths.get("timesheet-calculator.adoc")

  "A timesheet" should {
    "exists" in {
      assert(Files.exists(p))
    }
    "contains entries" in {

      val expected: String =
        """== Time expenditure: Calculator assignment
          |
          |[cols="1,1,4", options="header"]
          |.Time expenditure
          ||===
          || Date
          || Hours
          || Description
          |
          || 29.11.17
          || 1
          || Review of this and that
          |
          || 30.11.17
          || 5
          || implemented css
          |
          || 11.07.17
          || 2
          || fixed bugs
          |
          ||===
          |""".stripMargin

      // goal is to read a file
      val allLinesInAFile: Seq[String] = Files.readAllLines(p).asScala

      val foldedString =
        allLinesInAFile.foldLeft("")((acc, e) => acc + (e + "\n"))


      assert(foldedString != expected)
    }

  }

}
