package fhj.swengb.assignments.tree

import at.fhj.swengb.assignments.tree.MathUtil
import org.scalatest.{Matchers, WordSpecLike}

class MathUtilSpec extends WordSpecLike with Matchers {

  "some basic tests" in {
    0.001 shouldEqual MathUtil.round(0.001232)
    0.002 shouldEqual MathUtil.round(0.001532)
    1.339 shouldEqual MathUtil.round(1.339)
  }

}
