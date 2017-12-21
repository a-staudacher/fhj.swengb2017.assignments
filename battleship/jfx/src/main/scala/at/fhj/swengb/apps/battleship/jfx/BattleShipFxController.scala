package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.nio.file._
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Slider, TextArea}
import javafx.scene.layout.GridPane

import at.fhj.swengb.apps.battleship.BattleShipProtobuf
import at.fhj.swengb.apps.battleship.BattleShipProtocol._
import at.fhj.swengb.apps.battleship.model.{BattleField, BattleShipGame, Fleet, FleetConfig}


class BattleShipFxController extends Initializable {


  @FXML private var battleGroundGridPane: GridPane = _
  var bsGame: BattleShipGame = _

  /**
    * A text area box to place the history of the game
    */
  @FXML private var log: TextArea = _

  @FXML private var slider: Slider = _
  def newGame(): Unit = initGame()

  def saveGame(): Unit = {
    val pGame: BattleShipProtobuf.BattleShipGame = convert(bsGame)

    val path = Paths.get("target/BattleShipProtobuf.bin")
    val outstream = Files.newOutputStream(path)

    pGame.writeTo(outstream)

    println("Wrote to " + path.toAbsolutePath.toString)
  }

  def slided(): Unit = {
    init(bsGame);
    bsGame.refresh(slider.getValue.toInt)
    println(slider.getValue.toString)
    println(slider.getValue.toInt.toString)
  }

  def loadGame(): Unit = {
    val path = Paths.get("target/BattleShipProtobuf.bin")
    val in = Files.newInputStream(path)

    val protoGame: BattleShipProtobuf.BattleShipGame = BattleShipProtobuf.BattleShipGame.parseFrom(in)
    val a = BattleShipGame(convert(protoGame).battleField, getCellWidth, getCellHeight, appendLog)
    a.clickedCells = convert(protoGame).clickedCells
    init(a)
    println("Read Game from disc")
    bsGame.refresh(bsGame.clickedCells.length)
  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = initGame()

  private def getCellHeight(y: Int): Double = battleGroundGridPane.getRowConstraints.get(y).getPrefHeight

  private def getCellWidth(x: Int): Double = battleGroundGridPane.getColumnConstraints.get(x).getPrefWidth

  def appendLog(message: String): Unit = log.appendText(message + "\n")

  /**
    * Create a new game.
    *
    * This means
    *
    * - resetting all cells to 'empty' state
    * - placing your ships at random on the battleground
    *
    */
  def init(game : BattleShipGame) : Unit = {
    bsGame = game
    battleGroundGridPane.getChildren.clear()
    for (c <- game.getCells) {
      battleGroundGridPane.add(c, c.pos.x, c.pos.y)
    }
    game.getCells().foreach(c => c.init)
  }


  private def initGame(): Unit = {
    val game: BattleShipGame = createGame()
    init(game)
    appendLog("New game started.")
  }

  private def createGame(): BattleShipGame = {
    val field = BattleField(10, 10, Fleet(FleetConfig.Standard))

    val battleField: BattleField = BattleField.placeRandomly(field)

    BattleShipGame(battleField, getCellWidth, getCellHeight, appendLog)
  }

}