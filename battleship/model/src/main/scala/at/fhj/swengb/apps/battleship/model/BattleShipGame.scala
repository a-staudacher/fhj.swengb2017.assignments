package at.fhj.swengb.apps.battleship.model

/**
  * Contains all information about a battleship game.
  */
object BattleShipGame {
  var editMode: Boolean = _
  var shipType: Int = _
  var horizontal: Boolean = true
  var nrShip1: Int = 3
  var nrShip2: Int = 2
  var nrShip3: Int = 1
}
case class BattleShipGame(getCellWidth: Int => Double,
                          getCellHeight: Int => Double) {

  /**
    * remembers which vessel was hit at which position
    * starts with the empty map, meaning that no vessel was hit yet.
    *
    **/
  var hits: Map[Vessel, Set[BattlePos]] = Map()
  var myBattleField: BattleField = BattleField(10, 10, Fleet.Empty)

  var clickedCells: List[BattlePos] = List()

  /**
    * contains all vessels which are destroyed
    */
  var sunkShips: Set[Vessel] = Set()

  /**
    * We don't ever change cells, they should be initialized only once.
    */
  private var cells: Seq[BattleFxCell] = for {x <- 0 until myBattleField.width
                                              y <- 0 until myBattleField.height
                                              pos = BattlePos(x, y)} yield {
    BattleFxCell(BattlePos(x, y),
      getCellWidth(x),
      getCellHeight(y),
      myBattleField.fleet.findByPos(pos),
      updateGameState,
      hit,
      PlaceVessel)
  }

  def updateCells(): Unit = {
    cells.foreach(
      (cell:BattleFxCell) => cell.someVessel = myBattleField.fleet.findByPos(cell.pos)
    )
  }

  def PlaceVessel(pos: BattlePos): Unit = {
    println("TryPlaceVessel")
    val currentFleet = myBattleField.fleet
    val direction: Direction = if (BattleShipGame.horizontal) Horizontal else Vertical
    val vessel = Vessel(NonEmptyString("AwesomeVessel"), pos, direction, BattleShipGame.shipType)

    val ShipRemaining = BattleShipGame.shipType match {
      case 1 => BattleShipGame.nrShip1!=0
      case 2 => BattleShipGame.nrShip2!=0
      case 3 => BattleShipGame.nrShip3!=0
    }

    if (ShipRemaining && vessel.occupiedPos.subsetOf(myBattleField.availablePos)) {
      println("PlaceingVessel")
      myBattleField = BattleField(myBattleField.width, myBattleField.height,
        currentFleet.copy(vessels = currentFleet.vessels + vessel))
      println(myBattleField.fleet.vessels.toString)
      BattleShipGame.shipType match {
        case 1 => BattleShipGame.nrShip1 -= 1
        case 2 => BattleShipGame.nrShip2 -= 1
        case 3 => BattleShipGame.nrShip3 -= 1
      }
      updateCells()
      cells.foreach(c => c.init)
    }
  }

  def hit(pos:BattlePos):Unit = {
    if(!clickedCells.contains(pos))
      clickedCells = clickedCells :+ pos
  }

  def getCells(): Seq[BattleFxCell] = cells


  def updateGameState(vessel: Vessel, pos: BattlePos): Unit = {

    if (hits.contains(vessel)) {
      // this code is executed if vessel was already hit at least once
      // we want to update the hits map
      // the map should be updated if
      // we hit a vessel which is already contained
      // in the 'hits' map, and it's values (
      // the set of BattlePos) should be added
      // the current pos
      val oldPos: Set[BattlePos] = hits(vessel)
      hits = hits.updated(vessel, oldPos + pos)
      if (oldPos.contains(pos)) {
        //was allready hit, turn not over
      }

      if (vessel.occupiedPos == hits(vessel)) {
        //new Ship was sunk
        sunkShips = sunkShips + vessel
        if (myBattleField.fleet.vessels == sunkShips) {
        //game over
        }
      }


    } else {
      // vessel is not part of the map
      // but vessel was hit!
      // it was hit the first time ever!
      hits = hits.updated(vessel, Set(pos))
    }

  }


}
