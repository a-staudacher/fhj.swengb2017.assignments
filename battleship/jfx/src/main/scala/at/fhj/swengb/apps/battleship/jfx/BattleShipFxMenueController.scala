package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.layout.GridPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.util.{Failure, Success, Try}


class BattleShipFxMenueController extends Initializable {
  override def initialize(url: URL, rb: ResourceBundle): Unit = {}
  def bt_newgame(): Unit = {
  println("test")
  val scene: Scene = BattleShipFxApp.EditS
  BattleShipFxApp.loadScene(scene,BattleShipFxApp.rootStage)
}

  def bt_highscore():Unit={
  val scene: Scene = BattleShipFxApp.HighscoreS
  BattleShipFxApp.loadScene(scene,BattleShipFxApp.rootStage)
}

  def bt_credits():Unit = {}
}