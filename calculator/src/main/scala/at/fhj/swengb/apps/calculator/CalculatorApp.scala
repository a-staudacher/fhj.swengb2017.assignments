package at.fhj.swengb.apps.calculator

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Success}
import scala.util.control.NonFatal

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val fxml = "/at/fhj/swengb/apps/calculator/calculator.fxml"
  val css = "/at/fhj/swengb/apps/calculator/calculator.css"

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Calculator")
      setSkin(stage, fxml, css)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }

}

class CalculatorFxController extends Initializable {

  val calculatorProperty: ObjectProperty[RpnCalculator] = new SimpleObjectProperty[RpnCalculator](RpnCalculator())

  def getCalculator() : RpnCalculator = calculatorProperty.get()

  def setCalculator(rpnCalculator : RpnCalculator) : Unit = calculatorProperty.set(rpnCalculator)

  @FXML var num1 : TextField = _
  @FXML var num2 : TextField = _
  @FXML var num3 : TextField = _


  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def refresh(): Unit = {
    val calcu = getCalculator()
    if(calcu.stack.size>0) {
      num1.setText(calcu.stack(0) match {
        case Val(x) => x.toString
      })
      if (calcu.stack.size > 1) {
        calcu.stack foreach println
        num2.setText(calcu.stack(1) match {
          case Val(x) => x.toString
        })
        if (calcu.stack.size > 2) {
          num3.setText(calcu.stack(2) match {
            case Val(x) => x.toString
          })
        }
        else num3.setText("")
      }
      else {
        num2.setText("")
        num3.setText("")
      }
    }
    else {
      num1.setText("")
      num2.setText("")
      num3.setText("")
    }
    calcu.stack.size match {
      case 0 => {
        num1.setDisable(false)
        num2.setDisable(true)
        num3.setDisable(true)
      }
      case 1 => {
        num1.setDisable(true)
        num2.setDisable(false)
        num3.setDisable(true)
      }
      case 2 => {
        num1.setDisable(true)
        num2.setDisable(true)
        num3.setDisable(false)
      }
      case 3 => {
        num1.setDisable(true)
        num2.setDisable(true)
        num3.setDisable(true)
      }
    }
  }
  def enter(): Unit = {
    val num : String = {
      getCalculator().stack.size match {
        case 0 => {
          num1.getText()
        }
        case 1 => {
          num2.getText()
        }
        case 2 => {
          num3.getText()
        }
        case _ => "ung"
      }
    }
    if (num != "ung") {
      getCalculator().push(Op(num)) match {
        case Success(c) => setCalculator(c)
        case Failure(e) => // show warning / error
      }
      refresh()
    }
  }

  def plus(): Unit = {
    getCalculator().push(Add) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error
    }
    getCalculator().stack foreach println
    refresh()
  }

  def minus(): Unit = {
    getCalculator().push(Sub) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error
    }
    getCalculator().stack foreach println
    refresh()
  }

  def div(): Unit = {
    val divid = getCalculator().stack.size match {
      case 2 => {
        val a = num2.getText()
        num2.setText("Not a Number")
        num3.setDisable(true)
        num2.setDisable(false)
        a
      }
      case 3 => {
        val a = num3.getText()
        num3.setText("Not a Number")
        num3.setDisable(false)
        a
      }
    }
    if (divid != "0.0") {
      getCalculator().push(Div) match {
        case Success(c) => setCalculator(c)
        case Failure(e) => // show warning / error
      }
      refresh()
    }
    else {
      setCalculator(getCalculator().pop()._2)
    }
    getCalculator().stack foreach println

  }

  def mul(): Unit = {
    getCalculator().push(Mul) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => // show warning / error
    }
    getCalculator().stack foreach println
    refresh()
  }

  def clear(): Unit = {
    getCalculator().stack.size match {
      case 0 => {
        if(num1.getText != "0")
          num1.setText("0")
        else {
          setCalculator(RpnCalculator())
          refresh()
        }
      }
      case 1 => {
        if(num2.getText != "0")
          num2.setText("0")
        else {
          setCalculator(RpnCalculator())
          refresh()
        }
      }
      case 2 => {
        if(num3.getText != "0")
          num3.setText("0")
        else {
          setCalculator(RpnCalculator())
          refresh()
        }
      }
      case 3 => {
        setCalculator(RpnCalculator())
        refresh()
      }
    }
  }

  def comma(): Unit = {
    getCalculator().stack.size match {
      case 0 => {
        if (!num1.getText().contains("."))
          num1.setText(num1.getText()+".")
      }
      case 1 => {
        if (!num2.getText().contains("."))
          num2.setText(num2.getText()+".")
      }
      case 2 => {
        if (!num3.getText().contains("."))
          num3.setText(num3.getText()+".")
      }
    }
  }

  def write(text : String): Unit = {
      getCalculator().stack.size match {
      case 0 => num1.setText(num1.getText()+text)
      case 1 => num2.setText(num2.getText()+text)
      case 2 => num3.setText(num3.getText()+text)
      case _ =>
    }
  }
  def zero(): Unit = {
    write("0")
  }
  def one(): Unit = {
    write("1")
  }
  def two(): Unit = {
    write("2")
  }
  def three(): Unit = {
    write("3")
  }
  def four(): Unit = {
    write("4")
  }
  def five(): Unit = {
    write("5")
  }
  def six(): Unit = {
    write("6")
  }
  def seven(): Unit = {
    write("7")
  }
  def eigth(): Unit = {
    write("8")
  }
  def nine(): Unit = {
    write("9")
  }
  def sgn(): Unit = {
    getCalculator().stack.size match {
      case 0 => {
        if(num1.getText().contains("-"))
          num1.setText(num1.getText().tail)
        else
          num1.setText("-"+num1.getText())
      }
      case 1 => {
        if(num2.getText().contains("-"))
          num2.setText(num2.getText().tail)
        else
          num2.setText("-"+num2.getText())
      }
      case 2 => {
        if(num3.getText().contains("-"))
          num3.setText(num3.getText().tail)
        else
          num3.setText("-"+num3.getText())
      }
    }
  }

}