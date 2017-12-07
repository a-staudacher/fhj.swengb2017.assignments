package at.fhj.swengb.apps.calculator

/**
  * An operation which can be used with the RPN calculator.
  */
trait Op

object Op {

  /**
    * A convenience constructor to create an Op from a string. No error handling is performed.
    *
    * @param s
    * @return
    */
  def apply(s: String): Op = {
    s match {
      case "*" => Mul
      case "-" => Sub
      case "+" => Add
      case "/" => Div
      case value => Val(value.toDouble)
    }
  }

}

/**
  * A class representing a number which can be used in the RPN.
  *
  * @param value
  */
case class Val(value: Double) extends Op

/**
  * Denotes a binary operation (like addition, multiplication etc.) which can also be put on the stack.
  */
trait BinOp extends Op {
  def eval(left: Val, right: Val): Val
}

/**
  * A case class representing multiplication.
  */
case object Mul extends BinOp {
  override def eval(left: Val, right: Val): Val = Val(left.value * right.value)
}

/**
  * A case class representing division. Be aware that you have to think about the division through zero. In this case,
  * a Val(Double.NaN) shoudl be returned.
  */
case object Div extends BinOp {
  override def eval(left: Val, right: Val): Val = ???
}

/**
  * this object represents the addition operation.
  */
case object Add extends BinOp {
  override def eval(left: Val, right: Val): Val = Val(left.value + right.value)
}

/**
  * Represents the substraction operation.
  */
case object Sub extends BinOp {
  override def eval(left: Val, right: Val): Val = Val(left.value - right.value)
}
