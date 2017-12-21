package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship.model.BattleShipGame

object BattleShipProtocol {

  def convert(g : BattleShipGame) : BattleShipProtobuf.BattleShipGame = ???

  def convert(g : BattleShipProtobuf.BattleShipGame) : BattleShipGame = ???

}
