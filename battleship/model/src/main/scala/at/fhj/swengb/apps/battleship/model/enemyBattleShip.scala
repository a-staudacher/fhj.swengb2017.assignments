package at.fhj.swengb.apps.battleship.model

import at.fhj.swengb.apps.battleship.TCP

/**
  * Contains all information about a battleship game.
  */
object enemyBattleShip {

}

case class enemyBattleShip(getCellWidth: Int => Double,
                           getCellHeight: Int => Double,
                           tcp:TCP,
                           myCells:Seq[BattleFxCell]) {

  /**
    * remembers which vessel was hit at which position
    * starts with the empty map, meaning that no vessel was hit yet.
    *
    **/
  var clickedCells: List[BattlePos] = List()

  /**
    * contains all vessels which are destroyed
    */
  private var cells: Seq[BattleFXGameCell] = for {x <- 0 until 10
                                              y <- 0 until 10
                                              pos = BattlePos(x, y)} yield {
    BattleFXGameCell(BattlePos(x, y),
      getCellWidth(x),
      getCellHeight(y),
      updateGameState,
      tcp)
  }

  def getCells(): Seq[BattleFXGameCell] = cells

  var hits = 0
  def updateGameState(hit: Int): Unit = {

    if (hit == 1) {
      //hit
      hits+=1
      if (hits==10) {
        println("win")
      }
    }
    else if(hit==2){
      //water
    }
    if(hit!=3 && hits!=10) {
      //not hit a allready hit and not allready over
      getShot()
    }
  }
  var gotHit:Int=0
  def getShot():Unit={
    val shotPos = tcp.getShot()
    println("got Shot " ++ shotPos.toString)
    myCells.foreach((cell:BattleFxCell)=>
      if(cell.pos==shotPos){
        if(cell.someVessel.isDefined){
          gotHit+=1
          tcp.sendHit()
          if(gotHit==10)
            println("Lost")
        }
        else
          tcp.sendMiss()
      }

    )
  }


}
