package at.fhj.swengb.apps.battleship

import java.{lang, util}

import scala.collection.JavaConverters._
import at.fhj.swengb.apps.battleship.model._

object BattleShipProtocol {

  def convert(g : BattleShipGame) : BattleShipProtobuf.BattleShipGame = BattleShipProtobuf.BattleShipGame.newBuilder()
        .setBattleField(BattleShipProtobuf.BattleField.newBuilder()
            .setWidth(g.battleField.width)
            .setHeight(g.battleField.height)
            .setFleet(BattleShipProtobuf.Fleet.newBuilder().addAllVessel(g.battleField.fleet.vessels.map(convert).toIterable.asJava).build())
          .build())
          .addAllClickedCells(g.clickedCells.map(convert).toIterable.asJava)
    .build()

  def convert(g : BattleShipProtobuf.BattleShipGame) : BattleShipGame = {
    val width = g.getBattleField.getWidth
    val height = g.getBattleField.getHeight
    val fleet = g.getBattleField.getFleet.getVesselList.asScala.map(convert).toSet
    val battleField: BattleField = BattleField(width,height,Fleet(fleet))
    val game = BattleShipGame(battleField, ((x:Int) => x.toDouble), ((x:Int) => x.toDouble),(x=>()))
    g.getClickedCellsList.asScala.map(convert).foreach(game.hit)
    game
  }

  def convert(g: BattleShipProtobuf.Vessel) : Vessel = {
    val name = g.getName
    val direction = {
      g.getDirection match {
        case "H" => Horizontal
        case "V" => Vertical
      }
    }
    Vessel(NonEmptyString(name),convert(g.getStartPos),direction,g.getSize)
  }

  def convert(g: Vessel) : BattleShipProtobuf.Vessel = {
    BattleShipProtobuf.Vessel.newBuilder()
      .setName(g.name.value)
      .setStartPos(convert(g.startPos))
      .setSize(g.size)
      .setDirection(g.direction match {
        case Vertical => "V"
        case Horizontal => "H"
      })
      .build()
  }

  def convert(pos: BattlePos): BattleShipProtobuf.Pos = BattleShipProtobuf.Pos.newBuilder().setX(pos.x).setY(pos.y).build()
  def convert(pos: BattleShipProtobuf.Pos) : BattlePos = BattlePos(pos.getX,pos.getY)
}
