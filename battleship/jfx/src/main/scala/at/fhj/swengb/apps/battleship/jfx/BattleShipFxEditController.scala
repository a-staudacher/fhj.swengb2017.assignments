package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Button
import javafx.scene.layout.{Background, Border, GridPane}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.util.{Failure, Success, Try}

object BattleShipFxEditController {
}
class BattleShipFxEditController extends Initializable {
  @FXML private var btn_ship1:Button =_
  @FXML private var btn_ship2:Button =_
  @FXML private var btn_ship3:Button =_
  @FXML private var btn_ready:Button =_
  @FXML private var battleGroundGridPane: GridPane = _



  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    BattleShipGame.editMode = true
    initGameField()
    BattleShipGame.nrShip1 = 3
    BattleShipGame.nrShip2 = 2
    BattleShipGame.nrShip3= 1
    bt_ship1()
  }
  def bt_ready():Unit = {}
  def bt_ship1():Unit = {
    BattleShipGame.shipType = 1
    BattleShipGame.horizontal = !BattleShipGame.horizontal
    // change color
    changeBtn(btn_ship1)
  }
  def bt_ship2():Unit = {
    BattleShipGame.shipType = 2
    BattleShipGame.horizontal = !BattleShipGame.horizontal
    changeBtn(btn_ship2)
  }
  def bt_ship3():Unit = {
    BattleShipGame.shipType = 3
    BattleShipGame.horizontal = !BattleShipGame.horizontal
    changeBtn(btn_ship3)
  }
  def bt_back() :Unit = {
  }

  def changeBtn(button: Button):Unit = {
    btn_ship1.getStyleClass.add("button1")
    btn_ship2.getStyleClass.add("button1")
    btn_ship3.getStyleClass.add("button1")
    button.getStyleClass.remove("button1")
    btn_ship1.getStyleClass.remove("button2")
    btn_ship1.getStyleClass.remove("button3")
    btn_ship2.getStyleClass.remove("button2")
    btn_ship2.getStyleClass.remove("button3")
    btn_ship3.getStyleClass.remove("button2")
    btn_ship3.getStyleClass.remove("button3")
    if(BattleShipGame.horizontal)
      button.getStyleClass.add("button2")
    else
      button.getStyleClass.add("button3")
  }

  def initGameField():Unit = {
    BattleShipFxApp.bsGame=BattleShipGame(getCellWidth, getCellHeight)

    battleGroundGridPane.getChildren.clear()

    for (c <- BattleShipFxApp.bsGame.getCells) {
      battleGroundGridPane.add(c, c.pos.x, c.pos.y)
    }
    BattleShipFxApp.bsGame.getCells().foreach(c => c.init)
  }

  private def getCellHeight(y: Int): Double = battleGroundGridPane.getRowConstraints.get(y).getPrefHeight
  private def getCellWidth(x: Int): Double = battleGroundGridPane.getColumnConstraints.get(x).getPrefWidth
}