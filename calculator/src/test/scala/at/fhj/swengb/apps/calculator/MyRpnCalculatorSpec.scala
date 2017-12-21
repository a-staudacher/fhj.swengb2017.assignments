package at.fhj.swengb.apps.calculator

import org.scalatest.WordSpecLike

import scala.util.{Failure, Success}

/**
  * A specification for a reverse polish notation calculator.
  */
class MyRpnCalculatorSpec extends WordSpecLike {

  val EmptyCal: RpnCalculator = RpnCalculator()

  "Reverse Polish Notation Calculator" should {
    "be able to add one Val" in {
      RpnCalculator().push(Val(1)) match {
        case Success(cal) =>
          assert(cal.stack.nonEmpty)
          assert(cal.stack.size == 1)
          assert(cal.stack(0) == Val(1))
        case Failure(exception) => fail(exception.getMessage)
      }
    }
    "be able to put three vals on the stack" in {
      for {oneElem <- EmptyCal.push(Val(1.0))
           twoElem <- oneElem.push(Val(2.0))
           threeElem <- twoElem.push(Val(3.0))
      } {
        assert(oneElem.size == 1)
        assert(twoElem.size == 2)
        assert(threeElem.size == 3)
      }
    }

    "be able to add two vals" in {
      EmptyCal.push(Seq(Val(1.0), Val(2.0))) match {
        case Success(calculator) =>
          assert(calculator.stack.size == 2)
          assert(calculator.stack(0) == Val(1.0))
          assert(calculator.stack(1) == Val(2.0))

        case Failure(exception) => fail()
      }
    }
  }

}
