package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.gui.TextField

import se.ramn.krossaklossar.util
import se.ramn.krossaklossar.Highscore


class GameOver(val gameState: GameStates.Value) extends BasicGameState {
  lazy val font = util.Font.build

  private var highscoreEntryText = ""

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    if (gc.getInput.isKeyPressed(Input.KEY_M)) {
      game.enterState(GameStates.Menu.id)
    } else if (gc.getInput.isKeyPressed(Input.KEY_Q)) {
      gc.exit()
    } else if (gc.getInput.isKeyPressed(Input.KEY_ENTER)) {
      game.enterState(GameStates.AddHighscoreEntry.id)
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    val (points, levelsCompleted, levelsCount) =
      game.getState(GameStates.Playing.id) match {
        case p: Playing => (p.points, p.levelsCompleted, p.levelsCount)
        case _ => (0, 0, 0)
      }
    val allLevelsCompleted = levelsCompleted == levelsCount
    val message = if (allLevelsCompleted) "Victory!" else "Game over!"

    val menu = s"""
    |${message}
    |
    |Levels completed: ${levelsCompleted} of ${levelsCount}
    |Score: ${points}
    |
    |Press enter to add highscore
    |Press m for menu
    |Press q to quit
    |""".stripMargin.trim

    g.setFont(font)
    g.drawString(menu, 100, 100)
  }

  def getScoreData(game: StateBasedGame): (Int, Int, Int) = {
    game.getState(GameStates.Playing.id) match {
      case p: Playing => (p.points, p.levelsCompleted, p.levelsCount)
      case _ => (0, 0, 0)
    }
  }

  override def enter(gc: GameContainer, game: StateBasedGame) {
    super.enter(gc, game)
    gc.getInput.clearKeyPressedRecord()
    highscoreEntryText = game.getState(GameStates.AddHighscoreEntry.id) match {
      case e: AddHighscoreEntry =>
        val text = e.collectText
        if (text != "") {
          val (points, levelsCompleted, levelsCount) = getScoreData(game)
          Highscore.highscores = Highscore.highscores :+ Highscore(levelsCompleted, points, text)
        }
        text
      case _ => ""
    }
  }

  override def leave(gc: GameContainer, game: StateBasedGame) {
    super.leave(gc, game)
  }
}
