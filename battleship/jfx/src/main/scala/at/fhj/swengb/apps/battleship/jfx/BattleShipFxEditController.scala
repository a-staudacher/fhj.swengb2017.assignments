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


class BattleShipFxEditController extends Initializable {
  @FXML private var btn_ship1:Button =_
  @FXML private var btn_ship2:Button =_
  @FXML private var btn_ship3:Button =_

  var shipType: Int = 0
  var horizontal: Boolean = true
  var nrShip1: Int = 3
  var nrShip2: Int = 2
  var nrShip3: Int = 1

  override def initialize(url: URL, rb: ResourceBundle): Unit = {}
  def bt_ready():Unit = {}
  def bt_ship1():Unit = {
    shipType = 1
    horizontal = !horizontal
    // change color
    changeBtn(btn_ship1)
  }
  def bt_ship2():Unit = {
    shipType = 2
    horizontal = !horizontal
    changeBtn(btn_ship2)
  }
  def bt_ship3():Unit = {
    shipType = 3
    horizontal = !horizontal
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
    if(horizontal)
      button.getStyleClass.add("button2")
    else
      button.getStyleClass.add("button3")
  }
}