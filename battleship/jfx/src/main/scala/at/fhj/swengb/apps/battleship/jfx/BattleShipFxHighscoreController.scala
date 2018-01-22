package at.fhj.swengb.apps.battleship.jfx

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.URL
import java.nio.file._
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.layout.GridPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.io.Source
import scala.util.{Failure, Success, Try}


class BattleShipFxHighscoreController extends Initializable {
  var path: Path = _
  var outstream: BufferedReader = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
  //  val in = Source.fromFile("highscoreFile.csv").getLines().toList
    path = Paths.get("target/highscoreFile.csv")
    outstream = new BufferedReader(new InputStreamReader((Files.newInputStream(path))))
  }
  def loadGame():Unit = {}

  def bt_newgame(): Unit = {
    println("test")
    val scene: Scene = BattleShipFxApp.EditS
    BattleShipFxApp.loadScene(scene,BattleShipFxApp.rootStage)
  }

  def bt_highscore():Unit={
    var line = outstream.readLine()
    while(line != null)
    {
      println(line)
      line=outstream.readLine()
    }
  }

  def bt_credits():Unit = {}
}

