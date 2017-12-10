package at.fhj.swengb.apps.calculator


import java.util.NoSuchElementException

import sun.management.snmp.AdaptorBootstrap.DefaultValues

import scala.util.{Failure, Success, Try}

/**
  * Companion object for our reverse polish notation calculator.
  */
object RpnCalculator {

  /**
    * Returns empty RpnCalculator if string is empty, otherwise pushes all operations
    * on the stack of the empty RpnCalculator.
    *
    * @param s a string representing a calculation, for example '1 2 +'
    * @return
    */
  def apply(s: String): Try[RpnCalculator] = {
    if(s.isEmpty) Try(RpnCalculator(Nil))
    else RpnCalculator().push(s.split(' ').map((str) => Op(str)).toSeq)
  }

}

/**
  * Reverse Polish Notation Calculator.
  *
  * @param stack a datastructure holding all operations
  */
case class RpnCalculator(stack: List[Op] = Nil) {

  /**
    * By pushing Op on the stack, the Op is potentially executed. If it is a Val, it the op instance is just put on the
    * stack, if not then the stack is examined and the correct operation is performed.
    *
    * @param op
    * @return
    */
  def push(op: Op): Try[RpnCalculator] = {
    op match {
      case Val(x) => Try(RpnCalculator(stack :+Val(x)))
      case Add => {
        pop()._1 match {
          case Val(x) => pop()._2.pop()._1 match {
            case Val(y) => Try(RpnCalculator(stack.init.init :+Add.eval(Val(y), Val(x))))
            case _ => Try(this)
          }
          case _ => Try(this)
        }
      }
      case Sub => {
        pop()._1 match {
          case Val(x) => pop()._2.pop()._1 match {
            case Val(y) => Try(RpnCalculator(stack.init.init :+Sub.eval(Val(y), Val(x))))
            case _ => Try(this)
          }
          case _ => Try(this)
        }
      }
      case Mul => {
        pop()._1 match {
          case Val(x) => pop()._2.pop()._1 match {
            case Val(y) => Try(RpnCalculator(stack.init.init :+Mul.eval(Val(y), Val(x))))
            case _ => Try(this)
          }
          case _ => Try(this)
        }
      }
      case Div => {
        pop()._1 match {
          case Val(x) => pop()._2.pop()._1 match {
            case Val(y) => Try(RpnCalculator(stack.init.init :+Div.eval(Val(y), Val(x))))
            case _ => Try(this)
          }
          case _ => Try(this)
        }
      }
    }
  }

  /**
    * Pushes val's on the stack.
    *
    * If op is not a val, pop two numbers from the stack and apply the operation.
    *
    * @param op
    * @return
    */
  def push(op: Seq[Op]): Try[RpnCalculator] = {
    Try(op.foldLeft(RpnCalculator(stack))((ack,x) => ack.push(x).get))
  }

  /**
    * Returns an tuple of Op and a RevPolCal instance with the remainder of the stack.
    *
    * @return
    */
  def pop(): (Op, RpnCalculator) = (peek(),RpnCalculator(stack.init))

  /**
    * If stack is nonempty, returns the top of the stack. If it is empty, this function throws a NoSuchElementException.
    *
    * @return
    */
  def peek(): Op = {
    if (stack.size != 0) stack(stack.size-1) else {
      throw new NoSuchElementException
    }
  }

  /**
    * returns the size of the stack.
    *
    * @return
    */
  def size: Int = stack.size
}