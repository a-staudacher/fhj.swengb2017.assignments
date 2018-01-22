package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.util.{Failure, Success, Try}


class BattleShipFxConnectController extends Initializable {
  //@FXML private var lblLoad:TextField =_
  override def initialize(url: URL, rb: ResourceBundle): Unit = {}

  def btnHost(): Unit = {
    BattleShipFxApp.tcpConnection = TCP("host")
    val scene: Scene = BattleShipFxApp.EditS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

  def btnStartConnection(): Unit = {
    //BattleShipFxApp.tcpConnection = TCP(lblLoad.getText)
    val scene: Scene = BattleShipFxApp.EditS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

  def btnBack(): Unit = {
    val scene: Scene = BattleShipFxApp.MenuS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

}