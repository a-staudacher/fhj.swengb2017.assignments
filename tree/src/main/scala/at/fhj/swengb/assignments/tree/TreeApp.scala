package at.fhj.swengb.assignments.tree

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.effect.DropShadow
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{AnchorPane, BorderPane}
import javafx.scene.shape.{Line, StrokeType}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage


import scala.collection.JavaConversions._
import scala.util.control.NonFatal

/**
  * A simple JavaFX GUI for the tree assignment.
  */
object TreeApp {
  def main(args: Array[String]) {
    Application.launch(classOf[TreeApp], args: _*)
  }
}

class TreeApp extends javafx.application.Application {

  /**
    * GUI elements can be defined in an XML dialect. Using this approach,
    * GUI builders can be used more easily .
    */
  val Fxml = "/at/fhj/swengb/assignments/tree/Tree.fxml"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit = try {
    stage.setTitle("Tree assignment")
    loader.load[Parent]() // side effect
    val scene = new Scene(loader.getRoot[Parent])
    stage.setScene(scene)
    stage.show()

  } catch {
    case NonFatal(e) =>
      e.printStackTrace()
  }

}


class TreeAppController extends Initializable {

  import Graph._

  @FXML var borderPane: BorderPane = _
  val dropShadow = new DropShadow()

  val ev: EventHandler[MouseEvent] = (event: MouseEvent) => {
    event.getSource match {
      case l: Line if l.getEffect == null => l.setEffect(dropShadow)
      case l: Line if l.getEffect != null => l.setEffect(null)
      case _ => assert(false)
    }
  }

  val mouseEventHandler: EventHandler[MouseEvent] = (event: MouseEvent) => {
    event.getSource match {
      case a: AnchorPane =>
        a.getChildren.clear()
        a.getChildren.addAll(traverse(randomTree(Pt2D(event.getX, event.getY)))(paint))
      case _ => assert(false)
    }
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val pt: Pt2D = Pt2D(332, 370)

    val p = new AnchorPane()
    p.setOnMouseClicked(mouseEventHandler)
    p.getChildren.addAll(traverse(mkGraph(pt, 270, 100, 7))(paint))
    borderPane.setCenter(p)
  }

  def paint(line: L2D): Line = {
    val l = new Line(line.start.x, line.start.y, line.end.x, line.end.y)
    l.setStroke(line.color)
    l.setStrokeType(StrokeType.CENTERED)
    l.setEffect(dropShadow)
    l.setOnMouseEntered(ev)
    l.setOnMouseExited(ev)
    l
  }


}
