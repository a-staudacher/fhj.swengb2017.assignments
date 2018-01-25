package at.fhj.swengb.apps.battleship.model


object battlenamesGenerator {
  val adjective = List ("Disastrous", "Legendary", "Lost","Devastating", "Minor","Hostile","Traditional","Small","Enormous", "Gigantic")
    val noun = List ("fight", "battle", "combat","massacre","challenge","meeting","mock battle","sham fight", "games","competition")
    val preposition = List ("of","in","near","behind","left of", "right of", "without","among","on","in front of")
    val location = List ("Nowhere", "Bermuda Triangle", "Venice", "Barcelona", "Adelade","Hammerfest","Amsterdam", "Antwerpen","Vancouver","San Diego")
    val r = scala.util.Random
    def genName ():String = {
      val a = r.nextInt(adjective.length)
      val n = r.nextInt(noun.length)
      val p = r.nextInt(preposition.length)
      val l = r.nextInt(location.length)
      adjective.drop(a).head ++ " " ++ noun.drop(n).head ++" " ++ preposition.drop(p).head ++ " " ++location.drop(l).head
    }
}

class battlenamesGenerator {

}

