package se.ramn.krossaklossar

case class Highscore(levelsCompleted: Int, points: Int, name: String)

object Highscore {
  var highscores = List[Highscore]()
}
