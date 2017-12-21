package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship.model.BattleShipGame
import org.scalacheck.{Gen, Prop}
import org.scalatest.WordSpecLike
import org.scalatest.prop.Checkers


class BattleShipProtocolSpec extends WordSpecLike {

  import at.fhj.swengb.apps.battleship.model.BattleshipGameGen._

  "BattleShipProtocol" should {
    "be deserializable" in {
      Checkers.check(Prop.forAll(battleShipGameGen) {
        expected: BattleShipGame => {
          val actual = BattleShipProtocol.convert(BattleShipProtocol.convert(expected))
          actual == expected
        }
      })
    }
  }
}
