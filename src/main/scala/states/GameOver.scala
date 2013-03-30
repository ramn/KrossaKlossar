package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input

import se.ramn.krossaklossar.util


class GameOver(val gameState: GameStates.Value) extends BasicGameState {
  lazy val font = util.Font.build

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    if (gc.getInput.isKeyPressed(Input.KEY_M)) {
      game.enterState(GameStates.Menu.id)
    } else if (gc.getInput.isKeyPressed(Input.KEY_Q)) {
      sys.exit
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    val (points, levelsCompleted, levelsCount) =
      game.getState(GameStates.Playing.id) match {
        case p: Playing => (p.points, p.levelsCompleted, p.levelsCount)
        case _ => (0, 0, 0)
      }
    g.setFont(font)
    val allLevelsCompleted = levelsCompleted == levelsCount
    val message = if (allLevelsCompleted) "Victory!" else "Game over!"
    val menu = s"""
    |${message}
    |
    |Levels completed: ${levelsCompleted} of ${levelsCount}
    |Score: ${points}
    |
    |Press m for menu
    |Press q to quit
    |""".stripMargin.trim

    g.drawString(menu, 100, 100)
  }
}

