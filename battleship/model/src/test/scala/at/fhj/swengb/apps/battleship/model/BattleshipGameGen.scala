package at.fhj.swengb.apps.battleship.model

import org.scalacheck.Gen
import scala.collection.JavaConverters._

/**
  * Implement in the same manner like MazeGen from the lab, adapt it to requirements of BattleShip
  */

object BattleshipGameGen {

  val Battlefield: Gen[BattleField] = for {
    width <- Gen.chooseNum[Int](1, 20)
    height <- Gen.chooseNum[Int](1, 20)
  } yield BattleField(width, height, Fleet(FleetConfig.Standard))

  val battleShipGameGen: Gen[BattleShipGame] = for {
    battlefield <- Battlefield
  } yield BattleShipGame((x => x.toDouble), (x => x.toDouble))
}