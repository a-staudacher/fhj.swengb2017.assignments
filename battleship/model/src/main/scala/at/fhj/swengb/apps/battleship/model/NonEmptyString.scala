package at.fhj.swengb.apps.battleship.model

case class NonEmptyString(value: String) {
  require(value.nonEmpty, "value must not be empty.")
}
