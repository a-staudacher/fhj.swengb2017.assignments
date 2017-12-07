package at.fhj.swengb.apps.calculator

import org.scalatest.WordSpecLike

import scala.util.{Failure, Success, Try}

/**
  * A specification for a reverse polish notation calculator.
  */
class RpnCalculatorSpec extends WordSpecLike {

  val EmptyCal: RpnCalculator = RpnCalculator()

  "Reverse Polish Notation Calculator" should {
    "be able to add a Val" in {
      assert(EmptyCal.push(Val(0.0)).isSuccess)
    }

    "be able to put two vals on the stack" in {
      for {oneElem <- EmptyCal.push(Val(1.0))
           twoElem <- oneElem.push(Val(2.0))
      } {
        assert(twoElem.size == 2)
      }
    }
    "be able to add two vals" in {
      for {cal <- EmptyCal.push(Seq(Val(1.0), Val(2.0), Add))} {
        assert(cal.size == 1)
        val (value, c) = cal.pop()
        assert(value == Val(3.0))
        assert(c.size == 0)
      }
    }
    "be able to mul two vals" in {
      for {cal <- EmptyCal.push(Seq(Val(1.0), Val(2.0), Mul))} {
        assert(cal.size == 1)
        val (value, c) = cal.pop()
        assert(value == Val(2.0))
        assert(c.size == 0)
      }
    }
    "be able to div two vals" in {
      for {cal <- EmptyCal.push(Seq(Val(9.0), Val(3.0), Div))} {
        assert(cal.size == 1)
        val (value, c) = cal.pop()
        assert(value == Val(3.0))
        assert(c.size == 0)
      }
    }
    "be able to div through zero" in {
      val triedRevPolCal = EmptyCal.push(Seq(Val(9.0), Val(0.0), Div))
      for {cal <- triedRevPolCal} {
        val (value, c) = cal.pop()
        value match {
          case v: Val => assert(v.value.isNaN)
          case _ => fail()
        }
        assert(c.size == 0)
      }
    }
    "be able to mul two vals and have a look" in {
      for {cal <- EmptyCal.push(Seq(Val(1.0), Val(2.0), Mul))} {
        assert(cal.peek() == Val(2.0))
        assert(cal.size == 1)
      }
    }
    "throws empty exception" in {
      intercept[NoSuchElementException] {
        EmptyCal.pop()
      }
    }
    "RevPolCal('')" in assert(RpnCalculator("") == Success(RpnCalculator()))
    "RevPolCal('1')" in {
      RpnCalculator("1") match {
        case Success(cal) => assert(cal.peek == Val(1.0))
        case Failure(e) => fail(e)
      }
    }
    "RevPolCal('1 2 +')" in {
      RpnCalculator("1 2 +") match {
        case Success(cal) => assert(cal.peek == Val(3.0))
        case Failure(e) => fail(e)
      }
    }
    "RevPolCal('3 3 4 4 * * *')" in {
      RpnCalculator("3 3 4 4 * * *") match {
        case Success(cal) => assert(cal.peek == Val(144.0))
        case Failure(e) => fail(e)
      }
    }
    "RevPolCal('3 3 * 4 * 4 *')" in {
      RpnCalculator("3 3 * 4 * 4 *") match {
        case Success(cal) => assert(cal.peek == Val(144.0))
        case Failure(e) => fail(e)
      }
    }

    "RevPolCal('*')" in {
      RpnCalculator("*") match {
        case Failure(e: NoSuchElementException) =>
        case _ => fail()
      }
    }
    "RevPolCal('* 4')" in {
      RpnCalculator("* 4") match {
        case Failure(e: NoSuchElementException) =>
        case _ => fail()
      }
    }
    "RevPolCal('3 *')" in {
      RpnCalculator("3 *") match {
        case Failure(e: NoSuchElementException) =>
        case _ => fail()
      }
    }

  }

}
