package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.nio.file._
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Slider, TextArea}
import javafx.scene.layout.GridPane
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.{BattleShipProtobuf, TCP}
import at.fhj.swengb.apps.battleship.BattleShipProtocol._
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.util.{Failure, Success, Try}


class BattleShipFxController extends Initializable {


  @FXML private var battleGroundGridPane: GridPane = _
  var bsGame: BattleShipGame = _
  // 0 no ship selected, 1 ship 1 selected, 2 ship 2 selected, 3 ship 3 selected
  var shipType: Int = 0
  var horizontal: Boolean = true
  var nrShip1: Int = 3
  var nrShip2: Int = 2
  var nrShip3: Int = 1
  var tcp : TCP = _
  /**
    * A text area box to place the history of the game
    */
  @FXML private var log: TextArea = _


  def newGame(): Unit = initGame()

  def ship1(): Unit = {
    shipType = 1
    horizontal = !horizontal
    // change color
  }
  def ship2(): Unit = {
    shipType = 2
    horizontal = !horizontal
  }
  def ship3(): Unit = {
    shipType = 3
    horizontal = !horizontal
  }

  def finishEdit(): Unit = {
      tcp.sendEditFin()
      val t = new java.util.Timer()
      val task = new java.util.TimerTask {
        def run() = {
          if (tcp.readyRead) {
            tcp.read()
            this.cancel()
          }
        }
      }
    t.schedule(task, 1000)
  }

  def writeHighscore() : Unit ={
    if (Files.exists(Paths.get("target/highscore.csv"))) {

    }
  }

  def loadHighscore() : Unit ={

  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = initGame()

  private def getCellHeight(y: Int): Double = battleGroundGridPane.getRowConstraints.get(y).getPrefHeight

  private def getCellWidth(x: Int): Double = battleGroundGridPane.getColumnConstraints.get(x).getPrefWidth

  def appendLog(message: String): Unit = log.appendText(message + "\n")

  def init(game : BattleShipGame) : Unit = {
    bsGame = game
    battleGroundGridPane.getChildren.clear()
    for (c <- game.getCells) {
      battleGroundGridPane.add(c, c.pos.x, c.pos.y)
    }
    game.getCells().foreach(c => c.init)
  }

  private def initGame(): Unit = {
    val game: BattleShipGame = initEdit()
    init(game)
  }

  private def initEdit():BattleShipGame = {
    val field = BattleField(10, 10, Fleet.Empty)
    val battleField: BattleField = BattleField.placeRandomly(field)
    BattleShipGame(getCellWidth, getCellHeight)
  }

  def onClickEdit():Boolean ={
    //bsGame.
    true
  }

  def onReceive(msg:String):Unit ={
    msg match {
      case "finE" => {

      }
    }
  }

}