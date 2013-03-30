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
    g.setFont(font)
    val menu = """
    |Game Over!
    |
    |Press m for menu
    |Press q to quit
    |""".stripMargin.trim

    g.drawString(menu, 100, 100)
  }
}

