package at.fhj.swengb.apps.battleship.model


object FleetConfig {

  /**
    * Standard configuration for a fleet
    */
  val Standard: FleetConfig =
    FleetConfig(Map(classOf[BattleShip] -> 1,
      classOf[Cruiser] -> 2,
      classOf[Destroyer] -> 3,
      classOf[Submarine] -> 4)
    )

  val OneShip: FleetConfig = FleetConfig(Map(classOf[BattleShip] -> 1))

  val TwoShips: FleetConfig = FleetConfig(Map(classOf[BattleShip] -> 2))


}

/**
  * A configuration for a fleet. How many ships are there for which type
  *
  * @param vesselMap a map which tells us how many instances should be allowed for which vessel type
  */
case class FleetConfig(vesselMap: Map[Class[_ <: Vessel], Int]) {

  // for a given configuration, returns the total number of expected occupied battle positions
  lazy val numberOfTotalOccupiedPositions: Int = {
    vesselMap.map {
      case (tpe, count) => (tpe match {
        case t if t == classOf[BattleShip] => BattleShip.Size
        case t if t == classOf[Cruiser] => Cruiser.Size
        case t if t == classOf[Destroyer] => Destroyer.Size
        case t if t == classOf[Submarine] => Submarine.Size
        case _ => ???
      }) * count
    }.sum
  }

}