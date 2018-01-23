package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.util.{Failure, Success, Try}


class BattleShipFxConnectController extends Initializable {
  @FXML private var txipaddress:TextField =_
  @FXML private var lblLoad:Label =_

  override def initialize(url: URL, rb: ResourceBundle): Unit = {}

  def btnHost(): Unit = {
    println("host")
    BattleShipFxApp.tcpConnection = TCP("host")
    println("player connected")
    BattleShipFxApp.EditS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipedit.fxml")
    val scene: Scene = BattleShipFxApp.EditS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
    //lblLoad.setText("Waiting for player")
  }

  def btnStartConnection(): Unit = {
    println("connect to" ++ txipaddress.getText())
    println("connected")
    BattleShipFxApp.tcpConnection = TCP(txipaddress.getText)
    BattleShipFxApp.EditS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipedit.fxml")
    val scene: Scene = BattleShipFxApp.EditS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

  def btnBack(): Unit = {
    val scene: Scene = BattleShipFxApp.MenuS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

}