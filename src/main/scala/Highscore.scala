package se.ramn.krossaklossar

case class Highscore(levelsCompleted: Int, points: Int, name: String)

object Highscore {
  var highscores = List[Highscore]()

  override def toString = {
    highscores
      .sortBy(_.points)
      .reverse
      .map( h => h.points.toString + "  " +  h.name)
      .mkString("\n")
  }
}
