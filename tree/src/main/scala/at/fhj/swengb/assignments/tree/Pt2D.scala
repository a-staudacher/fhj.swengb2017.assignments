package at.fhj.swengb.assignments.tree


case class Pt2D(ax: Double, ay: Double) {

  lazy val x = MathUtil.round(ax)

  lazy val y = MathUtil.round(ay)

  lazy val normed = Pt2D(x, y)

}
