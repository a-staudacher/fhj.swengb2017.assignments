package fhj.swengb.assignments.tree

import javafx.scene.paint.Color

import at.fhj.swengb.assignments.tree.{L2D, Pt2D}
import org.scalatest.{Matchers, WordSpecLike}


class L2DSpec extends WordSpecLike with Matchers {

  val epsilon: Double = 0.00000000001

  "testLength" in {
    1.0 shouldEqual L2D(Pt2D(0, 0), Pt2D(0, 1), Color.GREEN).length
    1.0 shouldEqual L2D(Pt2D(0, 0), Pt2D(0, -1), Color.GREEN).length
    1.0 shouldEqual L2D(Pt2D(0, 0), Pt2D(1, 0), Color.GREEN).length
    1.0 shouldEqual L2D(Pt2D(0, 0), Pt2D(-1, 0), Color.GREEN).length
    1.4142135623730951 shouldEqual L2D(Pt2D(0, 0), Pt2D(1, 1), Color.GREEN).length
  }

  "testAngles" in {
    0 shouldEqual L2D(Pt2D(0, 0), Pt2D(1, 0), Color.GREEN).angle
    45 shouldEqual L2D(Pt2D(0, 0), Pt2D(1, 1), Color.GREEN).angle
    90 shouldEqual L2D(Pt2D(0, 0), Pt2D(0, 1), Color.GREEN).angle
    135 shouldEqual L2D(Pt2D(0, 0), Pt2D(-1, 1), Color.GREEN).angle
    180 shouldEqual L2D(Pt2D(0, 0), Pt2D(-1, 0), Color.GREEN).angle
    225 shouldEqual L2D(Pt2D(0, 0), Pt2D(-1, -1), Color.GREEN).angle
    270 shouldEqual L2D(Pt2D(0, 0), Pt2D(0, -1), Color.GREEN).angle
    315 shouldEqual L2D(Pt2D(0, 0), Pt2D(1, -1), Color.GREEN).angle

  }

  "doesConstructorOfL2Dwork" in {
    val o = Pt2D(0, 0)
    L2D(o, Pt2D(1, 0), Color.GREEN) shouldEqual L2D(o, 0, 1, Color.GREEN)
    L2D(o, Pt2D(0, 1), Color.GREEN) shouldEqual L2D(o, 90, 1, Color.GREEN)
    L2D(o, Pt2D(-1, 0), Color.GREEN) shouldEqual L2D(o, 180, 1, Color.GREEN)
    L2D(o, Pt2D(0, -1), Color.GREEN) shouldEqual L2D(o, -90, 1, Color.GREEN)
  }

}
