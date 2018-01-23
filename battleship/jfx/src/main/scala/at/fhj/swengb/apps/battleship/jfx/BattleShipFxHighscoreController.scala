package at.fhj.swengb.apps.battleship.jfx

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.URL
import java.nio.file._
import java.util.ResourceBundle
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.layout.GridPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.battleship.TCP
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}

import scala.io.Source
import scala.util.{Failure, Success, Try}
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.fxml.FXMLLoader
import javafx.scene.control.{TableView, TextField}
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.scene.control.TableColumn



class BattleShipFxHighscoreController extends Initializable {

  var num
  var title = _
  var score = _

  var path: Path = _
  var outstream: BufferedReader = _
  var tableView : Unit = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    val in = Source.fromFile("highscoreFile.csv").getLines().toList
    path = Paths.get("target/highscoreFile.csv")
    outstream = new BufferedReader(new InputStreamReader((Files.newInputStream(path))))
  }

  def bt_highscore():Unit= {
    var line = outstream.readLine().toArray[String]
    while(line != null)
    {
      title = line(0)
      score = line(1)
      num = ???

     tableView.getColumn("title")

    }
  }
  def btnClick1():Unit = {}
  def btnClick2():Unit = {}
  def btnClick3():Unit = {}
  def btnClick4():Unit = {}
  def btClick5():Unit = {}
  def btClick6():Unit = {}
  def btClick7():Unit = {}
  def btClick8():Unit = {}
  def btClick9():Unit = {}
  def btClick10():Unit = {}

  def btnBack():Unit = {
    val scene: Scene = BattleShipFxApp.MenuS
    BattleShipFxApp.loadScene(scene, BattleShipFxApp.rootStage)
  }

  def bt_credits():Unit = {}
}

