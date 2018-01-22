package at.fhj.swengb.apps.battleship

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.{ServerSocket, Socket}



object TCP {
  def apply(con:String):TCP = {
    con match {
      case "host" => {
        val sock = new ServerSocket(4444).accept()
        TCP(sock,1)
      }
      case con:String => {
        val sock: Socket = new Socket(con,4444)
        TCP(sock,2)
      }
    }
  }
}

case class TCP(sock:Socket,p:Int) {
  val is: BufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream))
  val os: PrintStream = new PrintStream(sock.getOutputStream())

  def read():String = {
    is.readLine()
  }

  def write(msg:String):Unit = {
    os.println(msg)
    os.flush()
  }
  def sendEditFin():Unit = {}
  def shot(x:Int,y:Int):Unit = {}
  def sendHit():Unit = {}
  def sendMiss():Unit = {}
  def sendShipDestroyed():Unit = {}
  def readyRead:Boolean = {true}
}
