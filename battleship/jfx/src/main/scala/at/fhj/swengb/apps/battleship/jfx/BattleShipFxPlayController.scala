package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model._

import scala.util.{Failure, Success, Try}


class BattleShipFxPlayController extends Initializable {
  @FXML private var battlePlayGroundGridPane: GridPane = _
  @FXML private var lb_player:Label =_

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    initGameField()
    lb_player.setText(BattleShipFxApp.gameName)
  }

  def btn_finish():Unit = {
    BattleShipFxApp.tcpConnection.close()
    val scene: Scene = BattleShipFxApp.MenuS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

  private def getCellHeight(y: Int): Double = battlePlayGroundGridPane.getRowConstraints.get(y).getPrefHeight
  private def getCellWidth(x: Int): Double = battlePlayGroundGridPane.getColumnConstraints.get(x).getPrefWidth

  def initGameField():Unit = {
    println(getCellWidth(5).toString())
    println(getCellHeight(5).toString())
    println(getCellWidth(5).toString())
    println(BattleShipFxApp.tcpConnection.toString)
    println(BattleShipFxApp.bsGame.getCells().length.toString)
    BattleShipFxApp.enemyBattleField=enemyBattleShip(getCellWidth,getCellHeight,BattleShipFxApp.tcpConnection,BattleShipFxApp.bsGame.getCells())

    battlePlayGroundGridPane.getChildren.clear()

    for (c <- BattleShipFxApp.enemyBattleField.getCells) {
      battlePlayGroundGridPane.add(c, c.pos.x, c.pos.y)
    }
    BattleShipFxApp.enemyBattleField.getCells().foreach(c => c.init)
    if(BattleShipFxApp.tcpConnection.p==2)
      BattleShipFxApp.enemyBattleField.getShot()
  }
}