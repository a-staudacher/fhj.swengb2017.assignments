package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship.model._
import org.scalacheck.Prop
import org.scalatest.WordSpecLike
import org.scalatest.prop.Checkers


class BattleShipProtocolSpec extends WordSpecLike {

  import at.fhj.swengb.apps.battleship.model.BattleshipGameGen._

  "BattleShipProtocol" should {
    "be deserializable" in {
      val battlefield = BattleField(10, 10, Fleet(FleetConfig.Standard))
      val expected = BattleShipGame((x => x.toDouble), (x => x.toDouble))
      expected.hit(BattlePos(1,2))
      //val actual = BattleShipProtocol.convert(BattleShipProtocol.convert(expected))
      //assert(actual.myBattleField == expected.myBattleField)
      //assert(actual.clickedCells == expected.clickedCells)
    }
  }
}


