package at.fhj.swengb.apps.battleship

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.{ServerSocket, Socket}

import at.fhj.swengb.apps.battleship.model.BattlePos



object TCP {
  def apply(con:String):TCP = {
    con match {
      case "host" => {
        val sock = new ServerSocket(6666).accept()
        TCP(sock,1)
      }
      case con:String => {
        val sock: Socket = new Socket(con,6666)
        TCP(sock,2)
      }
    }
  }
  var output:String=""
}

case class TCP(sock:Socket,p:Int) {
  val t = new java.util.Timer()
  val is: BufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream))
  val os: PrintStream = new PrintStream(sock.getOutputStream())

  val waitforRec = new java.util.TimerTask {
    def run() = {
      println("readyRead ?")
      if (readyRead) {
        TCP.output=read()
        this.cancel()
      }
    }
  }

  def read():String = {
    is.readLine()
  }

  def finishEdit(): Unit = {
    //t.scheduleAtFixedRate(waitforRec, 500,500)
    is.readLine()
  }

  def write(msg:String):Unit = {
    os.println(msg)
    os.flush()
    println("send: "++msg)
  }
  def sendEditFin():Unit = {
    write("efin")
  }
  def shot(pos:BattlePos):Unit = {
    write(pos.x.toString++","++pos.y.toString)
  }
  def getShot():BattlePos = {
    val a = read()
    val b = a.split(",")
    BattlePos(b.head.toInt,b.tail.head.toInt)
  }

  def sendHit():Unit = {
    write("1")
  }
  def sendMiss():Unit = {
    write("2")
  }

  def readyRead():Boolean = {
    is.ready()
  }
  def close():Unit = {
    sock.close()
  }
}
