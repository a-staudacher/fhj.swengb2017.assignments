package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.{Background, Border, GridPane}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model._

import scala.util.{Failure, Success, Try}

object BattleShipFxEditController {
}
class BattleShipFxEditController extends Initializable {
  @FXML private var btn_ship1:Button =_
  @FXML private var btn_ship2:Button =_
  @FXML private var btn_ship3:Button =_
  @FXML private var btn_ready:Button =_
  @FXML private var battleGroundGridPane: GridPane = _
  @FXML private var lb_positon:Label = _
  val t = new java.util.Timer()
  val waitforOut = new java.util.TimerTask {
    def run() = {
      if (TCP.output=="efin") {
        loadGame()
      }
    }
  }


  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    println("init Edit")
    BattleShipGame.editMode = true
    initGameField()
    BattleShipGame.nrShip1 = 3
    BattleShipGame.nrShip2 = 2
    BattleShipGame.nrShip3= 1
    bt_ship1()
  }
  def loadGame():Unit={
    BattleShipFxApp.GameS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/game.fxml")
    val scene: Scene = BattleShipFxApp.GameS
    BattleShipFxApp.loadScene(scene,BattleShipFxApp.rootStage)
  }

  def bt_ready():Unit = {
    if(BattleShipFxApp.tcpConnection.p==1){
      BattleShipFxApp.gameName = battlenamesGenerator.genName()
      BattleShipFxApp.tcpConnection.write(BattleShipFxApp.gameName)
      BattleShipFxApp.tcpConnection.finishEdit()
    }
    else {
      BattleShipFxApp.tcpConnection.sendEditFin()
      BattleShipFxApp.gameName=BattleShipFxApp.tcpConnection.read()
    }

    //t.scheduleAtFixedRate(waitforOut, 500,500)
    BattleShipGame.editMode=false
    println("loading gamescreen")
    loadGame()
    println("loaded gamescreen")
  }

  def bt_ship1():Unit = {
    BattleShipGame.shipType = 1
    BattleShipGame.horizontal = !BattleShipGame.horizontal
    // change color
    changeBtn(btn_ship1)
    setLabel()
  }
  def bt_ship2():Unit = {
    BattleShipGame.shipType = 2
    BattleShipGame.horizontal = !BattleShipGame.horizontal
    changeBtn(btn_ship2)
    setLabel()
  }
  def bt_ship3():Unit = {
    BattleShipGame.shipType = 3
    BattleShipGame.horizontal = !BattleShipGame.horizontal
    changeBtn(btn_ship3)
    setLabel()
  }
  def bt_back() :Unit = {
    val scene: Scene = BattleShipFxApp.MenuS
    BattleShipFxApp.loadScene(scene,BattleShipFxApp.rootStage)
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

  def setLabel():Unit = {
    if(BattleShipGame.horizontal)
      lb_positon.setText("Horizontal")
    else
      lb_positon.setText("Vertical")

  }
  private def getCellHeight(y: Int): Double = battleGroundGridPane.getRowConstraints.get(y).getPrefHeight
  private def getCellWidth(x: Int): Double = battleGroundGridPane.getColumnConstraints.get(x).getPrefWidth
}