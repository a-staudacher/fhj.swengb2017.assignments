package at.fhj.swengb.apps.battleship.model

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import at.fhj.swengb.apps.battleship._
;

/**
  * Represents one part of a vessel or one part of the ocean.
  */

case class BattleFxCell(pos: BattlePos
                        , width: Double
                        , height: Double
                        , var someVessel: Option[Vessel] = None
                        , fn: (Vessel, BattlePos) => Unit
                        , hit: (BattlePos) => Unit
                        , placeVessel: (BattlePos)=>Unit) extends Rectangle(width, height) {

  def init(): Unit = {
    if (someVessel.isDefined && BattleShipGame.editMode) {
      setFill(Color.YELLOWGREEN)
    } else {
      setFill(Color.BLUE)
    }
  }

  def updateEdit():Unit={

  }

  setOnMouseClicked(e => {
    /*hit(pos)
    someVessel match {
      case None =>
        log(s"Missed. Just hit water.")
        setFill(Color.AQUAMARINE)
      case Some(v) =>
        log(s"Hit an enemy vessel!")
        fn(v, pos)
        setFill(Color.RED)
    }*/
    if(BattleShipGame.editMode) {
      placeVessel(pos)
    }
    else
      {
        someVessel match {
          case None =>
            setFill(Color.AQUAMARINE)
          case Some(v) =>
            fn(v, pos)
            setFill(Color.RED)
        }
      }
  })

}
