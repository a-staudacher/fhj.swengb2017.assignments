package at.fhj.swengb.assignments.tree

import scala.math.BigDecimal.RoundingMode

object MathUtil {

  /**
    * rounds the given value to 3 decimal places.
    *
    * @param value  a double value
    * @return
    */
  def round(value: Double): Double = BigDecimal(value).setScale(3, RoundingMode.HALF_UP).doubleValue()

  /**
    * turns an angle given in degrees to a value in radiants.
    *
    * @param angle
    * @return
    */
  def toRadiants(angle: AngleInDegrees): AngleInRadiants = angle * Math.PI / 180

}
