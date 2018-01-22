package at.fhj.swengb.apps.battleship.jfx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame}

import scala.util.{Failure, Success, Try}

object BattleShipFxApp {
  var rootStage: Stage = _
  var SplashS: Scene = _
  var MenuS: Scene = _
  var ConnectS: Scene = _
  var EditS: Scene = _
  var GameS: Scene = _
  var EndS: Scene = _
  var HighscoreS: Scene = _
  var ReplayS: Scene = _
  var CreditS: Scene = _
  var bsGame: BattleShipGame = _
  //val enemyBattleField: BattleField = _
  var tcpConnection : TCP = _

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[BattleShipFxApp], args: _*)
  }

  def loadScene(scene: Scene, stage: Stage): Unit = {
    stage.setScene(scene)
    stage.show()
  }

  private def readFXML(file: String): Scene = {
    val scene: Scene = new Scene(FXMLLoader.load[Parent](getClass.getResource(file)))

    scene.getStylesheets.clear()
    scene.getStylesheets.add("/at/fhj/swengb/apps/battleship/jfx/css/BattleShip.css")

    scene
  }

}


class BattleShipFxApp extends Application {

  override def start(stage: Stage) = {
    stage.setTitle("BattleShip")

    BattleShipFxApp.rootStage = stage
    //BattleShipFxApp.loadScene(BattleShipFxApp.SplashS,BattleShipFxApp.rootStage)
    BattleShipFxApp.loadScene(BattleShipFxApp.SplashS,BattleShipFxApp.rootStage)
  }
  override def init(): Unit = {
    BattleShipFxApp.SplashS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipsplaschscreen.fxml")
    BattleShipFxApp.MenuS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipmenue.fxml")
    BattleShipFxApp.ConnectS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipmenue.fxml")
    BattleShipFxApp.EditS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipedit.fxml")
    BattleShipFxApp.GameS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/game.fxml")
    //BattleShipFxApp.EndS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipend.fxml")
    //BattleShipFxApp.HighscoreS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipmenue.fxml")
    //BattleShipFxApp.ReplayS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipmenue.fxml")
    //BattleShipFxApp.CreditS = BattleShipFxApp.readFXML("/at/fhj/swengb/apps/battleship/jfx/fxml/battleshipmenue.fxml")
  }
}