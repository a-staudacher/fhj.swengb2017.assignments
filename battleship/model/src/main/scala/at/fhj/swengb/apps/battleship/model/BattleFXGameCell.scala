package at.fhj.swengb.apps.battleship.model

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import at.fhj.swengb.apps.battleship.TCP
;

/**
  * Represents one part of a vessel or one part of the ocean.
  */

case class BattleFXGameCell(pos: BattlePos
                        , width: Double
                        , height: Double
                        , afterClick: (Int) => Unit
                        , tcp:TCP) extends Rectangle(width, height) {
  var allreadyHit=false
  def init(): Unit = {
      setFill(Color.BLUE)
  }

  def updateEdit():Unit={

  }

  setOnMouseClicked(e => {
    if(!allreadyHit) {
      tcp.shot(pos)
      val a = tcp.read()
      a match {
        case "1" => {
          println("treffer")
          setFill(Color.RED)
          afterClick(1)

        }
        case "2" => {
          println("wasser")
          setFill(Color.AQUAMARINE)
          afterClick(2)

        }
      }
    }
    else
      afterClick(3)
    allreadyHit=true
  })


}
