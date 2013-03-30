package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.Music
import org.newdawn.slick.Image
import org.newdawn.slick.SpriteSheet
import org.newdawn.slick.geom.{Shape, Rectangle}

import se.ramn.krossaklossar.util


class Menu(val gameState: GameStates.Value) extends BasicGameState {
  lazy val font = util.Font.build

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    if (gc.getInput.isKeyPressed(Input.KEY_P)) {
      game.enterState(GameStates.Playing.id)
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    g.setFont(font)
    val menu = """
    |Welcome to Krossa Klossar!
    |
    |Press p to play
    |
    |While playing, new balls can be bought by pressing b. This costs points!
    |""".stripMargin.trim

    g.drawString(menu, 100, 100)
  }
}
